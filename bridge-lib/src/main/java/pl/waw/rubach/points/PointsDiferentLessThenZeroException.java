package pl.waw.rubach.points;

public class PointsDiferentLessThenZeroException extends BridgeException {
    /**
     * exeption checking if point difference between
     * expected poinst for game (with fit, assumpotin etc)
     * and points for contract (calulated with bridge rulles
     * is biger then zero to be possible check in imp table
     *
     */
    //pyt na przyszłość może zrobić żeby zapisało w jakim przypadku to było? bo takiego błędu nie powinno być - to nie błąd użytkownika  - a może takiego wyjątku nie powinno wcale być

    PointsDiferentLessThenZeroException() {
        super("Błąd różnicy punktów - mniejsza od zera- to jakiś błąd programu");
        //second option of message: "Róznica punktow nie może być ujemna - bład programu chyba"
    }
}
