package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.vorstellungswaehler;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;

/**
 * Formatierer für eine {@link Vorstellung}.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
class VorstellungFormatierer
{
    private Vorstellung _vorstellung;

    /**
     * Initialisiert einen Formatierer für die angegebene Vorstellung.
     * 
     * @param vorstellung die Vorstellung, die von diesem Formatierer
     *            dargestellt wird.
     */
    public VorstellungFormatierer(Vorstellung vorstellung)
    {
        _vorstellung = vorstellung;
    }

    /**
     * Gibt die Vorstellung zurück, die von diesem Formatierer dargestellt wird.
     */
    Vorstellung getVorstellung()
    {
        return _vorstellung;
    }

    @Override
    public String toString()
    {
        return _vorstellung.getAnfangszeit().getFormatiertenString() + " - "
                + _vorstellung.getFilm().getFormatiertenString() + ", "
                + _vorstellung.getKinosaal().getName();
    }
}
