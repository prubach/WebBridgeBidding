package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.MEDIUMNUMBEROFPOINS;

public class InvalidFitException extends BridgeException{

  /**
   * Exception of  invalid  parameters to this method of scoring -
   * is not possible both have fit with 20 points because it should be mark only spades in this case according rules
   * @param numberPoints of pair
   * @param fitWe          if we have 8 card in major suit
   * @param fitThey        if they have 8 cards in major suit
   */
  public InvalidFitException(float numberPoints, boolean fitWe, boolean fitThey) {
    super(!fitWe || !fitThey || numberPoints != MEDIUMNUMBEROFPOINS ? WRONG_EXCEPTION_CASE_MESSAGE :
        "Nie mogą obie pary mieć fitu w starszym kolorze  przy "+ MEDIUMNUMBEROFPOINS +" PC -bo wtedy dla pików zapisuje się fit dla kierów brak fitu. " +
            "Podano: " + numberPoints + " punktów oraz fity: My:" + fitWe + "  Oni:" + fitThey + " - popraw zaznaczenie fitów lub punktów");

  }

}
