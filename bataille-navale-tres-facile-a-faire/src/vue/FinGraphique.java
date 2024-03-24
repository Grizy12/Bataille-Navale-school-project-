package vue;
import modele.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public class FinGraphique extends JFrame implements ActionListener{
    public JLabel texte = new JLabel();
    public JButton buttonOui = new JButton("Oui");
    public JButton buttonNon = new JButton("Non");
    public String vainqueur;
    public int reponse;
    public FinGraphique(String vainqueur){
        super("Fin de la partie");
        this.vainqueur = vainqueur;
        this.setSize(500, 350);
        this.reponse = 0;

        JPanel contentPane = new JPanel();

        texte.setText("Le vainqueur est  "+this.vainqueur+"  Souhaitez-vous rejouer ?");
        texte.setFont(new Font("Arial", Font.PLAIN, 15));
        texte.setBounds(10,10,450,150);

        buttonOui.setBounds(40,120,120,80);
        buttonOui.setFont(new Font("Arial", Font.PLAIN, 40));
        buttonOui.addActionListener(this);

        buttonNon.setBounds(230,120,120,80);
        buttonNon.setFont(new Font("Arial", Font.PLAIN, 40));
        buttonNon.addActionListener(this);

        contentPane.add(texte);
        contentPane.add(buttonOui);
        contentPane.add(buttonNon);


        contentPane.setLayout(null);

        this.setContentPane(contentPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

//sert a savoir si on relance une partie ou pas;
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == buttonOui){
            this.reponse = 1;
        }
        if(e.getSource()== buttonNon){
            this.reponse = 2;
        }
        this.dispose();
    }
    public int getReponse(){
      System.out.println(this.reponse);//sert aux tests
      return this.reponse;
    }
}
