package edu.mermet.tp5.fenetres;

import javax.swing.Action;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import edu.mermet.tp5.Application;

/**
 *
 * @author brunomermet
 */
public abstract class AbstractFenetreInterne extends JInternalFrame {
    private Action action;
    public AbstractFenetreInterne(Application appli, Action monAction, String nom) {
        super(nom, true,true,true,true);
        action = monAction;
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.addInternalFrameListener(new EcouteurFenetre());
    }
    
    private class EcouteurFenetre extends InternalFrameAdapter {
        @Override
        public void internalFrameClosing(InternalFrameEvent ife) {
            action.setEnabled(true);
        }
        @Override
        public void internalFrameActivated(InternalFrameEvent ife) {
        	action.setEnabled(false);
        }
    }
}
