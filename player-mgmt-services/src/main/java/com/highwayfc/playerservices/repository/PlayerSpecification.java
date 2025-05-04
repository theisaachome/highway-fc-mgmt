package com.highwayfc.playerservices.repository;

import com.highwayfc.playerservices.domain.model.Player;
import com.highwayfc.playerservices.domain.model.PlayerStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PlayerSpecification {
    public static Specification<Player> fullNameContains(String fullName) {
        return (root, query, cb) -> fullName == null ? null : cb.like(cb.lower(root.get("fullName")), "%" + fullName.toLowerCase() + "%");
    }

    public static Specification<Player> hasNationality(String nationality) {
        return (root, query, cb) -> nationality == null ? null : cb.equal(root.get("nationality"), nationality);
    }

    public static Specification<Player> hasStatus(PlayerStatus status) {
        return (root, query, cb) -> status == null ? null : cb.equal(root.get("status"), status);
    }
    public static Specification<Player> hasPosition(String position) {
        return (root, query, cb) -> position == null ? null :
                cb.equal(cb.lower(root.get("position")), position.toLowerCase());
    }

    public static Specification<Player> hasBirthDate(LocalDate birthDate) {
        return (root, query, cb) -> birthDate == null ? null : cb.equal(root.get("birthDate"), birthDate);
    }
}
