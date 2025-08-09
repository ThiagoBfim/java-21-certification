package questions.hard;

public class Question2 {

    enum Card {
        CLUB, DIAMOND, HEART, SPADE;
    }

    public static void main(String[] args) {
        Card card = Card.CLUB;
        printCardColor(card);
        printCardColor(null);
    }

//    private static void printCardColor(Card card) {
//        String color = null;
//        if (card != null) {
//            color = switch (card.ordinal()) {
//                case 0, 1 -> "red";
//                case 2, 3 -> "black";
//            };
//        }
//        System.out.println(color);
//    }

//    private static void printCardColor(Card card) {
//        String color = switch (Card.valueOf(card.toString())) {
//            case DIAMOND, HEART -> "red";
//            case SPADE, CLUB -> "black";
//        };
//        System.out.println(color);
//    }

//    private static void printCardColor(Card card) {
//        String color = switch (card) {
//            case HEART, DIAMOND -> "red";
//            case SPADE, CLUB -> "black";
//            default -> null;
//        };
//        System.out.println(color);
//    }

    private static void printCardColor(Card card) {
        String color = switch (card) {
            case null -> null;
            case HEART, DIAMOND -> "red";
            case SPADE, CLUB -> "black";
        };
        System.out.println(color);
    }

//    private static void printCardColor(Card card) {
//        String color = null;
//        if (card == Card.HEART || card == Card.DIAMOND) {
//            color = "red";
//        }
//        if (card == Card.SPADE || card == Card.CLUB) {
//            color = "black";
//        }
//        System.out.println(color);
//    }

}
