/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Controleur.Controleur;
import Modele.Plateau;
import Modele.Piece;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Dimitri
 */
public class FenetreJeu extends Vue implements KeyListener{
    private Score score;
    private Grid grid;
    private Pieces pieces;
    private JLabel titre;
    
    private Controleur controleur;
    
    public FenetreJeu(Plateau plateau){
        super();
        controleur = new Controleur(plateau);
        score = new Score();
        grid = new Grid(plateau.getTailleX(),plateau.getTailleY(),2);
        pieces = new Pieces(plateau.getSuivantes(),plateau.getSuivantes().length);
        
        ImageIcon icon = new ImageIcon(scaleImage(new ImageIcon(getClass().getResource("/images/tetris-logo.png")).getImage(),400,150,50));
        titre = new JLabel(icon);
        titre.setPreferredSize(new Dimension(this.getWidth(), 200));
        
        this.getContentPane().add(titre, BorderLayout.EAST);
        this.getContentPane().add(score, BorderLayout.WEST);
        this.getContentPane().add(grid, BorderLayout.CENTER);
        this.getContentPane().add(pieces, BorderLayout.EAST);
        
        this.setVisible(true);
    }
    
    public void updateGrid (Plateau plateau){
        Color[][] tab = new Color [plateau.getTailleX()][plateau.getTailleY()];
        
        for (int i=0; i<plateau.getTailleX();i++){
            for (int j=0; j<plateau.getTailleY();j++){
                tab[i][j]=Piece.getColor(plateau.getPlateau()[i][j]);
            }
        }
        grid.updateGrid(tab);
        pieces.updatePiece(plateau.getSuivantes());
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'l':
                controleur.setAction(1);//rotation acw;
                break;
            case 'm':
                controleur.setAction(2);//rotation cw;
                break;
            case 'd':
                controleur.setAction(3);//déplacement vers la droite
                break;
            case 'q':
                controleur.setAction(4);//déplacement vers la gauche
                break;
            default :
                //nope
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar()== 's'){
            controleur.setAction(5);//accélérer vers le bas
        }       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyChar()=='s'){
                controleur.setAction(6);//ralentir vers le bas
        }
    }
}