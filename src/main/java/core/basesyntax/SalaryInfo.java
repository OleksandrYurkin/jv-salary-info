package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int DATE_INDEX = 0;

    private static final int NAME_INDEX = 1;

    private static final int HOURS_INDEX = 2;

    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder stringBuilder = new StringBuilder();

        LocalDate from = LocalDate.parse(dateFrom, formatter);

        LocalDate to = LocalDate.parse(dateTo, formatter);

        int[] earned = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");
            LocalDate recordDate = LocalDate.parse(parts[DATE_INDEX], formatter);
            if (!recordDate.isBefore(from) && !recordDate.isAfter(to)) {
                for (int i = 0; i < names.length; i++) {
                    if (parts[NAME_INDEX].equals(names[i])) {
                        earned[i] = (Integer.parseInt(parts[HOURS_INDEX])
                                * Integer.parseInt(parts[RATE_INDEX]))
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
