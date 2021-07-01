package fr.esgi.poke_exchange_api.domain.friendship;

import fr.esgi.poke_exchange_api.domain.friendship.mappers.FriendshipEntityMapper;
import fr.esgi.poke_exchange_api.domain.friendship.mappers.FriendshipMapper;
import fr.esgi.poke_exchange_api.domain.user.UserNotFoundException;
import fr.esgi.poke_exchange_api.domain.user.UserService;
import fr.esgi.poke_exchange_api.domain.user.models.User;
import fr.esgi.poke_exchange_api.exposition.friendship.FriendshipRequest;
import fr.esgi.poke_exchange_api.infrastructure.friendship.FriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendshipService {

    private final UserService userService;
    private final FriendshipRepository repository;

    public List<User> findFriendsOf(UUID id) {
        try {
            var user = this.userService.findOneById(id);
            var friendships = this.findAllAccepted();
            var friends = new ArrayList<User>();

            var userFriendships = friendships.stream()
                    .filter(friendship -> user.getId().equals(friendship.getClaimer())
                            || user.getId().equals(friendship.getReceiver()))
                    .collect(Collectors.toList());

            if (!userFriendships.isEmpty()) {
                userFriendships.forEach(friendship -> {
                    if (friendship.getClaimer().equals(user.getId())) {
                        friends.add(this.userService.findOneById(friendship.getReceiver()));
                    }
                    else {
                        friends.add(this.userService.findOneById(friendship.getClaimer()));
                    }
                });
            }

            return friends;

        } catch (UserNotFoundException exception) {
            throw new UserNotFoundException();
        }
    }

    public List<Friendship> findPendingFriendshipsOf(UUID id) {
        try {
            var user = this.userService.findOneById(id);
            var friendships = this.findAllPending();

            return friendships.stream()
                    .filter(friendship -> user.getId().equals(friendship.getClaimer())
                            || user.getId().equals(friendship.getReceiver()))
                    .filter(friendship -> !friendship.isAccepted())
                    .collect(Collectors.toList());

        } catch (UserNotFoundException exception) {
            throw new UserNotFoundException();
        }
    }

    public Friendship findOneById(Long id) {
        var entity = this.repository.findById(id);

        if (entity.isEmpty()) {
            throw new FriendshipNotFoundException();
        }

        var mapper = new FriendshipMapper();

        return mapper.from(entity.get());
    }

    public Friendship acceptFriendship(Long id, UUID receiver) {
        var optional = this.repository.findById(id);
        if (optional.isEmpty()) {
            throw new FriendshipNotFoundException();
        }

        var entity = optional.get();
        if (entity.isAccepted()) {
            throw new AlreadyFriendsException();
        }

        if (!entity.getReceiver().equals(receiver)) {
            throw new FriendshipReceiverMismatchException();
        }

        entity.setAccepted(true);
        entity = this.repository.save(entity);

        return this.findOneById(entity.getId());
    }

    public Friendship requestFriendship(FriendshipRequest body) {
        if (isInvalidFriendship(body)) {
            throw new ImpossibleFriendshipException();
        }

        if (usersAreAlreadyFriends(body)) {
            throw new AlreadyFriendsException();
        }

        if (isPending(body)) {
            var friendship = findOnePending(body);
            if (body.getClaimer().equals(friendship.getClaimer())) {
                throw new FriendshipAlreadyExistsException();
            }

            return this.acceptFriendship(friendship.getId(), body.getClaimer());
        }

        var entityMapper = new FriendshipEntityMapper();
        var entity = this.repository.save(entityMapper.from(body));

        return this.findOneById(entity.getId());
    }

    public void deleteFriendship(Long id) {
        var entity = this.repository.findById(id);

        if (entity.isEmpty()) {
            throw new FriendshipNotFoundException();
        }

        this.repository.deleteById(id);
    }

    private boolean isPending(FriendshipRequest request) {
        var pendingFriendships = this.findAllPending();

        for (var friendship : pendingFriendships) {
            if ((friendship.getClaimer().equals(request.getClaimer())
                    && friendship.getReceiver().equals(request.getReceiver()))
                || (friendship.getClaimer().equals(request.getReceiver())
                    && friendship.getReceiver().equals(request.getClaimer())))
            {
                return true;
            }
        }

        return false;
    }

    private List<Friendship> findAll() {
        var entities = this.repository.findAll();
        var friendships = new ArrayList<Friendship>();

        for (var entity : entities) {
            friendships.add(this.findOneById(entity.getId()));
        }

        return friendships;
    }

    private List<Friendship> findAllPending() {
        var friendships = this.findAll();

        return friendships.stream()
                .filter(friendship -> !friendship.isAccepted())
                .collect(Collectors.toList());
    }

    private List<Friendship> findAllAccepted() {
        var friendships = this.findAll();

        return friendships.stream()
                .filter(Friendship::isAccepted)
                .collect(Collectors.toList());
    }

    private Friendship findOnePending(FriendshipRequest request) {
        var pendingFriendships = this.findAllPending();

        for (var friendship : pendingFriendships) {
            if ((friendship.getClaimer().equals(request.getClaimer())
                    && friendship.getReceiver().equals(request.getReceiver()))
                || (friendship.getClaimer().equals(request.getReceiver())
                    && friendship.getReceiver().equals(request.getClaimer())))
            {
                return friendship;
            }
        }

        throw new FriendshipNotFoundException();
    }

    private boolean usersAreAlreadyFriends(FriendshipRequest request) {
        var friendsOf = this.findFriendsOf(request.getClaimer());
        var ids = friendsOf.stream()
                .map(User::getId)
                .collect(Collectors.toList());

        return ids.contains(request.getReceiver());
    }

    private boolean isInvalidFriendship(FriendshipRequest request) {
        return request.getClaimer().equals(request.getReceiver());
    }
}
