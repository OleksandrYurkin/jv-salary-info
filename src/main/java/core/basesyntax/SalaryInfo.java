package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder stringBuilder = new StringBuilder();

        LocalDate from = LocalDate.parse(dateFrom, formatter);

        LocalDate to = LocalDate.parse(dateTo, formatter);

        int[] earned = new int[names.length];

        if (names == null || data == null || dateFrom == null || dateTo == null) {
            throw new IllegalArgumentException("Input must not be null");
        }

        for (String record : data) {
            String[] parts = record.split(" ");
            LocalDate d = LocalDate.parse(parts[0], formatter);
            if (!d.isBefore(from) && !d.isAfter(to)) {
                for (int i = 0; i < names.length; i++) {
                    if (parts[1].equals(names[i])) {
                        earned[i] = (Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]))
                                + earned[i];
                    }
                }
            }
        }
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(" - ").append(earned[i]);
            if (i < names.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}
