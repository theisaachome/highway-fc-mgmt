package com.highwayfc.playerservices.utils;

import com.highwayfc.playerservices.repository.PlayerRepo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class PlayerIDGenerator {
    private final PlayerRepo playerRepo;
    public PlayerIDGenerator(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMM");
//    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    public  String generatePlayerId(LocalDate registerDate, String clubName) {
        String datePart = registerDate.format(DATE_FORMAT);
        String clubPart = clubName.replaceAll("\\s+", "").toUpperCase();

        String numberPart = String.format("%03d", this.playerRepo.count());

        return datePart + clubPart + numberPart;
    }
}
