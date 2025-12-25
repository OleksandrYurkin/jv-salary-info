package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final int PARTS_COUNT = 4;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);

        String lineSeparator = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            int totalEarned = 0;

            for (String record : data) {
                String[] parts = record.split(" ");
                if (parts.length != PARTS_COUNT) {
                    continue;
                }

                LocalDate recordDate = LocalDate.parse(parts[DATE_INDEX], DATE_FORMATTER);
                if (recordDate.isBefore(from) || recordDate.isAfter(to)) {
                    continue;
                }

                if (!names[i].equals(parts[NAME_INDEX])) {
                    continue;
                }

                int hours = Integer.parseInt(parts[HOURS_INDEX]);
                int rate = Integer.parseInt(parts[RATE_INDEX]);
                totalEarned += hours * rate;
            }

            sb.append(lineSeparator)
                    .append(names[i])
                    .append(" - ")
                    .append(totalEarned);
        }

        return sb.toString();
    }
}
