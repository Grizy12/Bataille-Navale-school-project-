package vue;
import modele.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public class PrenomGraphique extends JFrame implements ActionListener{
    public JLabel text = new JLabel();
    public JTextField ecrire = new JTextField();
    public JButton button = new JButton("Ok");
    protected String name;
    public boolean enCours;


    public PrenomGraphique(){
        super("Choisisez votre prenom");
        this.setSize(375, 250);
        this.name = null;
        this.enCours = true;
        JPanel contentPane = new JPanel();

        text.setText("Entrez votre nom");
        text.setBounds(120,10,200,100);

        ecrire.setBounds(100,90,165,25);

        button.setBounds(80,140,200,25);
        button.addActionListener(this);


        contentPane.add(text);
        contentPane.add(ecrire);
        contentPane.add(button);

        contentPane.setLayout(null);

        this.setContentPane(contentPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public String getName(){
      return this.name;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == button){
          this.name = ecrire.getText();
          if (name.length() == 0) {
            this.name = "Jack Sparrow";
          }
          this.enCours = false;
        }
    }
}
