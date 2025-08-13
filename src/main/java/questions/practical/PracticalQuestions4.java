package questions.practical;

import java.time.*;

public class PracticalQuestions4 {


}

class Date {

    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0, 0);

        System.out.println(Duration.between(
                ZonedDateTime.of(date, ZoneId.of("Europe/Lisbon")), //0
                ZonedDateTime.of(date, ZoneId.of("America/Sao_Paulo")) //-3
        )); //PT3H

        System.out.println(Duration.between(
                OffsetTime.of(date.toLocalTime(), ZoneOffset.of("-04:00")),
                OffsetTime.of(date.toLocalTime(), ZoneOffset.of("-05:00"))
        )); //PT1H

        System.out.println(Duration.between(
                OffsetTime.of(date.toLocalTime(), ZoneOffset.UTC).plusHours(1),
                OffsetTime.of(date.toLocalTime(), ZoneOffset.of("+01:00"))
        )); //PT-2H


        System.out.println(Duration.between(
                ZonedDateTime.of(LocalDateTime.of(2022, Month.NOVEMBER, 6,1,0),
                        ZoneId.of("US/Eastern")),//-4
                ZonedDateTime.of(LocalDateTime.of(2022, Month.NOVEMBER, 6,2,0),
                        ZoneId.of("US/Eastern"))//-5
        )); //PT2H - -4-5=1+1=2


        System.out.println(Duration.between(
                ZonedDateTime.of(LocalDateTime.of(2022, Month.NOVEMBER, 6,2,0),
                        ZoneId.of("US/Eastern")),//-5
                ZonedDateTime.of(LocalDateTime.of(2022, Month.NOVEMBER, 6,1,0),
                        ZoneId.of("US/Eastern"))//-4
        )); //PT-2H = -5-4=-1+(-1)


        System.out.println(Duration.between(
                LocalDateTime.of(2022, Month.MARCH, 11,2,0),
                LocalDateTime.of(2022, Month.MARCH, 11,3,0)
        )); //PT1H

        System.out.println(Duration.between(
                OffsetTime.of(date.toLocalTime(), ZoneOffset.of("-05:00")),
                OffsetTime.of(date.toLocalTime(), ZoneOffset.of("-04:00"))
        )); //PT-1H

        System.out.println(Duration.between(
                ZonedDateTime.of(LocalDateTime.of(2022, Month.MARCH, 13,2,0),
                        ZoneId.of("US/Eastern")),//-5
                ZonedDateTime.of(LocalDateTime.of(2022, Month.MARCH, 13,3,0),
                        ZoneId.of("US/Eastern"))//-4
        )); //PT0S
    }
}