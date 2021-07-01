package fr.esgi.poke_exchange_api.exposition.friendship;

import fr.esgi.poke_exchange_api.domain.friendship.*;
import fr.esgi.poke_exchange_api.domain.user.UserNotFoundException;
import fr.esgi.poke_exchange_api.domain.user.mappers.UserResponseMapper;
import fr.esgi.poke_exchange_api.domain.user.models.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/friendships")
@RequiredArgsConstructor
public class FriendshipController {

    private final FriendshipService service;
    private final UserResponseMapper userMapper;

    @PostMapping("/request")
    public ResponseEntity<Friendship> sendFriendshipRequest(@RequestBody FriendshipRequest request) {
        try {
            var friendship = this.service.requestFriendship(request);
            return ResponseEntity.ok(friendship);

        } catch (AlreadyFriendsException | FriendshipAlreadyExistsException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (ImpossibleFriendshipException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{id}/accept")
    public ResponseEntity<Friendship> acceptFriendshipRequest(
            @PathVariable Long id,
            @RequestBody FriendshipAccept accept)
    {
        try {
            var friendship = this.service.acceptFriendship(id, accept.getReceiver());
            return ResponseEntity.ok(friendship);

        } catch (FriendshipNotFoundException exception) {
            return ResponseEntity.notFound().build();
        } catch (FriendshipReceiverMismatchException exception) {
            return ResponseEntity.badRequest().build();
        } catch (AlreadyFriendsException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/pending/user/{id}")
    public ResponseEntity<List<Friendship>> findFriendshipsPending(@PathVariable UUID id) {
        try {
            var friendships = this.service.findPendingFriendshipsOf(id);
            return ResponseEntity.ok(friendships);

        } catch (UserNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{id}/friends")
    public ResponseEntity<List<UserResponse>> findFriendsOf(@PathVariable UUID id) {
        try {
            var response = new ArrayList<UserResponse>();
            var users = this.service.findFriendsOf(id);
            users.forEach(user -> response.add(this.userMapper.from(user)));

            return ResponseEntity.ok(response);

        } catch (UserNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            this.service.deleteFriendship(id);
            return ResponseEntity.ok().build();
        } catch (FriendshipNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
