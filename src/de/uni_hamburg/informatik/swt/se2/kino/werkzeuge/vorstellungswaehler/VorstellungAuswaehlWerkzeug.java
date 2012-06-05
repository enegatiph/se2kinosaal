package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.vorstellungswaehler;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Tagesplan;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;

/**
 * Mit diesem Werkzeug kann der Benutzer oder die Benutzerin eine Vorstellung
 * aus einem Tagesplan auswählen.
 * 
 * Dieses Werkzeug ist ein eingebettetes Subwerkzeug. Es benachrichtigt seine
 * Beobachter, wenn sich die ausgewählte Vorstellung geändert hat.
 */
public class VorstellungAuswaehlWerkzeug
{
    private VorstellungAuswaehlWerkzeugUI _ui;

    // Das Material dieses Werkzeugs
    private Tagesplan _tagesplan;

    /**
     * Initialisiert das Werkzeug.
     */
    public VorstellungAuswaehlWerkzeug()
    {
        _ui = new VorstellungAuswaehlWerkzeugUI();

        registriereUIAktionen();
    }

    /**
     * Diese Methode wird aufgerufen, wenn eine Vorstellung ausgewaehlt wurde.
     */
    private void vorstellungWurdeAusgewaehlt()
    {
    }

    /**
     * Gibt das Panel dieses Subwerkzeugs zurück. Das Panel sollte von einem
     * Kontextwerkzeug eingebettet werden.
     * 
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _ui.getUIPanel();
    }

    /**
     * Gibt die derzeit ausgewählte Vorstellung zurück. Wenn keine Vorstellung
     * ausgewählt ist, wird <code>null</code> zurückgegeben.
     */
    public Vorstellung getAusgewaehlteVorstellung()
    {
        Vorstellung result = null;
        VorstellungFormatierer adapter = (VorstellungFormatierer) _ui
                .getVorstellungAuswahlList().getSelectedValue();
        if (adapter != null)
        {
            result = adapter.getVorstellung();
        }

        return result;
    }

    /**
     * Setzt den Tagesplan, dessen Vorstellungen zur Auswahl angeboten werden.
     * 
     * @require tagesplan != null
     */
    public void setTagesplan(Tagesplan tagesplan)
    {
        assert tagesplan != null : "Vorbedingung verletzt: tagesplan != null";

        _tagesplan = tagesplan;
        List<Vorstellung> vorstellungen = _tagesplan.getVorstellungen();
        aktualisiereAngezeigteVorstellungsliste(vorstellungen);
    }

    /**
     * Aktualisiert die Liste der Vorstellungen.
     */
    private void aktualisiereAngezeigteVorstellungsliste(
            List<Vorstellung> vorstellungen)
    {
        VorstellungFormatierer[] varray = new VorstellungFormatierer[vorstellungen
                .size()];
        for (int i = 0; i < vorstellungen.size(); i++)
        {
            varray[i] = new VorstellungFormatierer(vorstellungen.get(i));
        }
        _ui.getVorstellungAuswahlList().setListData(varray);
        _ui.getVorstellungAuswahlList().setSelectedIndex(0);
    }

    private void registriereUIAktionen()
    {
        _ui.getVorstellungAuswahlList().addListSelectionListener(
                new ListSelectionListener()
                {
                    @Override
                    public void valueChanged(ListSelectionEvent event)
                    {
                        if (!event.getValueIsAdjusting())
                        {
                            vorstellungWurdeAusgewaehlt();
                        }
                    }
                });
    }
}
