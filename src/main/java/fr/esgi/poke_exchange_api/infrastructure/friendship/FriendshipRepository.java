package fr.esgi.poke_exchange_api.infrastructure.friendship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository<FriendshipEntity, Long> { }
