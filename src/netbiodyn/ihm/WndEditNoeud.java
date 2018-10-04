/* This file is part of NetBioDyn.
 *
 *   NetBioDyn is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 3 of the License, or
 *   any later version.
 *
 *   NetBioDyn is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with NetBioDyn; if not, write to the Free Software
 *   Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
/*
 * WndGererEntites.java
 *
 * Created on 1 novembre 2007, 09:40
 */
package netbiodyn.ihm;

import netbiodyn.util.UtilFileFilter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import netbiodyn.Behavior;
import netbiodyn.Entity;
import netbiodyn.util.Lang;
import netbiodyn.util.RandomGen;

/**
 *
 * @author ballet
 */
public class WndEditNoeud extends javax.swing.JDialog {

    private final ArrayList<Entity> entities;
    private final ArrayList<Behavior> behaviours;
    private String DialogResult = "";
    public Entity entity = null;
    String _old_name = "";
    Color _old_color = Color.black;

    /**
     * Creates new form WndGererEntites
     *
     * @param entities
     * @param behaviours
     */
    public WndEditNoeud(ArrayList<Entity> entities, ArrayList<Behavior> behaviours) {
        this.setModal(true);
        this.entities = entities;
        this.behaviours = behaviours;
        initComponents();
    }

    public void WndCliValue_Load(Entity reaxel) {
        // Set language
        if (Lang.getInstance().getLang().equals("FR")) {
            jLabelNom.setText("Nom");
            jLabelEntite.setText("Agent");
            jLabelVie.setText("1/2 Vie (0=infini)");
            jLabelApp.setText("Apparence");
            jCheckBox_vidable.setText("Vidable");
            jLabelDescr.setText("Description");
            button_CANCEL.setText("Annuler");
            buttonCouleur.setText("Couleur");
            button_pas_image.setText("Pas d'image");

            String[] formes_fr = {"Disque", "Carre", "Triangle", "Losange", "Etoile", "Pois", "Bruit"};
            DefaultComboBoxModel model_fr = new DefaultComboBoxModel(formes_fr);
            comboBox_formes.setModel(model_fr);

        } else {
            jLabelNom.setText("Name");
            jLabelEntite.setText("Agent");
            jLabelVie.setText("1/2 Life (0=infinite)");
            jLabelApp.setText("Appearance");
            jCheckBox_vidable.setText("Cleanable");
            jLabelDescr.setText("Description");
            button_CANCEL.setText("Cancel");
            buttonCouleur.setText("Color");
            button_pas_image.setText("No image");

            String[] formes_fr = {"Disc", "Square", "Triangle", "Diamon", "Star", "Pots", "Noise"};
            DefaultComboBoxModel model_fr = new DefaultComboBoxModel(formes_fr);
            comboBox_formes.setModel(model_fr);
        }

        if (reaxel != null) {
            setCli(reaxel);
        } else {
            entity = new Entity();
            entity.Couleur = new Color(RandomGen.getInstance().nextInt(250), RandomGen.getInstance().nextInt(250), RandomGen.getInstance().nextInt(250));
        }

        _old_name = entity._etiquettes;
        _old_color = entity.Couleur;

        textBox1.setText(entity._etiquettes);
        textBox_mvt.setText(((Double) entity.mvt_proba).toString());

        // Apparence
        buttonCouleur.setBackground(entity.Couleur);
        comboBox_formes.setSelectedIndex(entity._forme);

        richTextBox_description.setText(entity.getDescription().getText());
        jCheckBox_vidable.setSelected(entity.Vidable);
        jCheckBox_invisible.setSelected(entity.isInvisible());
        //this.setSize(new Dimension(WndEditEntite.WIDTH,WndEditEntite.HEIGHT+320));
        textBox_mvt.setText(((Double) entity.mvt_proba).toString());
        jToggleButtonLeft.setSelected(!(entity.mvt_gauche));        
        jToggleButtonRight.setSelected(!(entity.mvt_droite));        
        jToggleButtonUp.setSelected(!(entity.mvt_haut));        
        jToggleButtonDown.setSelected(!(entity.mvt_bas));        
        jToggleButtonFront.setSelected(!(entity.mvt_devant));        
        jToggleButtonBack.setSelected(!(entity.mvt_derriere));        
        
    }

    public Entity getCli() {
        return entity;
    }

    public void setCli(Entity _cli) {
        this.entity = _cli;
    }

    public String getDialogResult() {
        return DialogResult;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNom = new javax.swing.JLabel();
        jLabelVie = new javax.swing.JLabel();
        jLabelApp = new javax.swing.JLabel();
        textBox1 = new javax.swing.JTextField();
        textBox_mvt = new javax.swing.JTextField();
        comboBox_formes = new javax.swing.JComboBox();
        buttonCouleur = new javax.swing.JButton();
        jCheckBox_invisible = new javax.swing.JCheckBox();
        button_img = new javax.swing.JButton();
        button_pas_image = new javax.swing.JButton();
        button_OK = new javax.swing.JButton();
        button_CANCEL = new javax.swing.JButton();
        jLabelDescr = new javax.swing.JLabel();
        button_aide_description = new javax.swing.JButton();
        jLabelEntite = new javax.swing.JLabel();
        richTextBox_description = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        jCheckBox_vidable = new javax.swing.JCheckBox();
        jLabelVie1 = new javax.swing.JLabel();
        textBox_demie_vie = new javax.swing.JTextField();
        jLabelVie2 = new javax.swing.JLabel();
        jLabelVie3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jToggleButtonFront = new javax.swing.JToggleButton();
        jToggleButtonUp = new javax.swing.JToggleButton();
        jToggleButtonLeft = new javax.swing.JToggleButton();
        jToggleButtonRight = new javax.swing.JToggleButton();
        jToggleButtonDown = new javax.swing.JToggleButton();
        jToggleButtonBack = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();

        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(204, 204, 255));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabelNom.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabelNom.setText("Nom");
        getContentPane().add(jLabelNom);
        jLabelNom.setBounds(10, 40, 50, 15);

        jLabelVie.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabelVie.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelVie.setText("Demi-vie (0=infinie)");
        getContentPane().add(jLabelVie);
        jLabelVie.setBounds(10, 200, 130, 15);

        jLabelApp.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabelApp.setText("Apparence");
        getContentPane().add(jLabelApp);
        jLabelApp.setBounds(10, 240, 70, 15);

        textBox1.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        textBox1.setText("nom");
        textBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textBox1KeyTyped(evt);
            }
        });
        getContentPane().add(textBox1);
        textBox1.setBounds(60, 40, 220, 20);

        textBox_mvt.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        textBox_mvt.setText("0");
        getContentPane().add(textBox_mvt);
        textBox_mvt.setBounds(150, 120, 60, 20);

        comboBox_formes.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        comboBox_formes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Disque", "Carre", "Triangle", "Losange", "Etoile", "Pois", "Bruit" }));
        getContentPane().add(comboBox_formes);
        comboBox_formes.setBounds(160, 240, 120, 21);

        buttonCouleur.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        buttonCouleur.setText("Couleur");
        buttonCouleur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCouleurMouseClicked(evt);
            }
        });
        getContentPane().add(buttonCouleur);
        buttonCouleur.setBounds(80, 240, 80, 23);

        jCheckBox_invisible.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jCheckBox_invisible.setText("Invisible");
        jCheckBox_invisible.setToolTipText("");
        jCheckBox_invisible.setContentAreaFilled(false);
        jCheckBox_invisible.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox_invisible.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(jCheckBox_invisible);
        jCheckBox_invisible.setBounds(180, 310, 110, 20);

        button_img.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        button_img.setText("Image");
        button_img.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_imgMouseClicked(evt);
            }
        });
        getContentPane().add(button_img);
        button_img.setBounds(80, 270, 80, 23);

        button_pas_image.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        button_pas_image.setText("Sans image");
        button_pas_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_pas_imageMouseClicked(evt);
            }
        });
        getContentPane().add(button_pas_image);
        button_pas_image.setBounds(160, 270, 120, 23);

        button_OK.setBackground(new java.awt.Color(153, 255, 153));
        button_OK.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        button_OK.setText("OK");
        button_OK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_OKMouseClicked(evt);
            }
        });
        button_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_OKActionPerformed(evt);
            }
        });
        getContentPane().add(button_OK);
        button_OK.setBounds(0, 350, 290, 40);

        button_CANCEL.setBackground(new java.awt.Color(255, 153, 153));
        button_CANCEL.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        button_CANCEL.setText("Annuler");
        button_CANCEL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_CANCELMouseClicked(evt);
            }
        });
        button_CANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CANCELActionPerformed(evt);
            }
        });
        getContentPane().add(button_CANCEL);
        button_CANCEL.setBounds(290, 350, 220, 40);

        jLabelDescr.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabelDescr.setText("Description de l'agent");
        getContentPane().add(jLabelDescr);
        jLabelDescr.setBounds(310, 200, 141, 15);

        button_aide_description.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        button_aide_description.setText("?");
        button_aide_description.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_aide_descriptionMouseClicked(evt);
            }
        });
        button_aide_description.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_aide_descriptionActionPerformed(evt);
            }
        });
        getContentPane().add(button_aide_description);
        button_aide_description.setBounds(430, 40, 40, 23);

        jLabelEntite.setBackground(new java.awt.Color(153, 153, 255));
        jLabelEntite.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        jLabelEntite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEntite.setText("Agent");
        getContentPane().add(jLabelEntite);
        jLabelEntite.setBounds(21, -2, 460, 34);

        richTextBox_description.setColumns(20);
        richTextBox_description.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        richTextBox_description.setLineWrap(true);
        richTextBox_description.setRows(5);
        richTextBox_description.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(richTextBox_description);
        richTextBox_description.setBounds(310, 220, 170, 110);

        jLabel19.setBackground(new java.awt.Color(153, 153, 255));
        jLabel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel19.setOpaque(true);
        getContentPane().add(jLabel19);
        jLabel19.setBounds(0, 0, 510, 30);

        jCheckBox_vidable.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jCheckBox_vidable.setSelected(true);
        jCheckBox_vidable.setText("Vidable");
        jCheckBox_vidable.setToolTipText("");
        jCheckBox_vidable.setContentAreaFilled(false);
        jCheckBox_vidable.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox_vidable.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(jCheckBox_vidable);
        jCheckBox_vidable.setBounds(10, 310, 110, 20);

        jLabelVie1.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabelVie1.setText("Directions=");
        getContentPane().add(jLabelVie1);
        jLabelVie1.setBounds(250, 120, 54, 15);

        textBox_demie_vie.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        textBox_demie_vie.setText("0");
        getContentPane().add(textBox_demie_vie);
        textBox_demie_vie.setBounds(150, 200, 130, 20);

        jLabelVie2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabelVie2.setText("Deplacement");
        getContentPane().add(jLabelVie2);
        jLabelVie2.setBounds(10, 120, 62, 15);

        jLabelVie3.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabelVie3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelVie3.setText("Proba=");
        getContentPane().add(jLabelVie3);
        jLabelVie3.setBounds(90, 120, 50, 15);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        jToggleButtonFront.setBackground(new java.awt.Color(153, 255, 153));
        jToggleButtonFront.setSelected(true);
        jToggleButtonFront.setText("O");
        jToggleButtonFront.setToolTipText("Devant");
        jToggleButtonFront.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonFrontActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonFront);
        jToggleButtonFront.setBounds(10, 10, 50, 23);

        jToggleButtonUp.setBackground(new java.awt.Color(153, 255, 153));
        jToggleButtonUp.setText("^");
        jToggleButtonUp.setToolTipText("Haut");
        jToggleButtonUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonUpActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonUp);
        jToggleButtonUp.setBounds(60, 10, 50, 23);

        jToggleButtonLeft.setBackground(new java.awt.Color(153, 255, 153));
        jToggleButtonLeft.setText("<");
        jToggleButtonLeft.setToolTipText("Gauche");
        jToggleButtonLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonLeftActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonLeft);
        jToggleButtonLeft.setBounds(30, 40, 50, 23);

        jToggleButtonRight.setBackground(new java.awt.Color(153, 255, 153));
        jToggleButtonRight.setText(">");
        jToggleButtonRight.setToolTipText("Droite");
        jToggleButtonRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonRightActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonRight);
        jToggleButtonRight.setBounds(90, 40, 50, 23);

        jToggleButtonDown.setBackground(new java.awt.Color(153, 255, 153));
        jToggleButtonDown.setText("v");
        jToggleButtonDown.setToolTipText("Bas");
        jToggleButtonDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonDownActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonDown);
        jToggleButtonDown.setBounds(60, 70, 50, 23);

        jToggleButtonBack.setBackground(new java.awt.Color(153, 255, 153));
        jToggleButtonBack.setSelected(true);
        jToggleButtonBack.setText("X");
        jToggleButtonBack.setToolTipText("Derriere");
        jToggleButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonBackActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonBack);
        jToggleButtonBack.setBounds(110, 70, 50, 23);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(310, 80, 170, 100);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(290, 190, 10, 150);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(0, 70, 510, 10);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(0, 230, 290, 10);
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(0, 340, 510, 10);
        getContentPane().add(jSeparator5);
        jSeparator5.setBounds(0, 190, 510, 10);
        getContentPane().add(jSeparator6);
        jSeparator6.setBounds(0, 300, 290, 10);

        setSize(new java.awt.Dimension(527, 447));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_CANCELMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_CANCELMouseClicked
// TODO add your handling code here:

    }//GEN-LAST:event_button_CANCELMouseClicked

    private void button_aide_descriptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_aide_descriptionMouseClicked
        // TODO add your handling code here:
        if (Lang.getInstance().getLang().equals("FR")) {
            JOptionPane.showMessageDialog(this, "Placer ici du texte decrivant cette entite. L'accès a cette description se fera aussi par un clic droit sur une entite dans le simulateur.", "Information", JOptionPane.INFORMATION_MESSAGE, null);
        } else {
            JOptionPane.showMessageDialog(this, "Put a short description of this entity. This description is also displayed by a right-click in the simulator.", "Information", JOptionPane.INFORMATION_MESSAGE, null);
        }

    }//GEN-LAST:event_button_aide_descriptionMouseClicked

    private void button_pas_imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_pas_imageMouseClicked
        // TODO add your handling code here:
        entity.BackgroundImage = null;
        entity._str_image_deco = "";
    }//GEN-LAST:event_button_pas_imageMouseClicked

    private void button_imgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_imgMouseClicked
        // TODO add your handling code here:
        // Placement de l'image dans le fond de la pictbox
        JFileChooser openFileDialog1 = new JFileChooser();
        UtilFileFilter filtre = new UtilFileFilter("Images", "bmp", "gif", "jpeg", "jpg", "png");
        openFileDialog1.setFileFilter(filtre);
        openFileDialog1.setDialogTitle("Image");
        openFileDialog1.setSelectedFile(new File(entity._str_image_deco));
        int returnval = openFileDialog1.showOpenDialog(this);
        if (returnval == JFileChooser.APPROVE_OPTION) {
            String fileName = openFileDialog1.getCurrentDirectory().getAbsolutePath() + File.separatorChar + openFileDialog1.getSelectedFile().getName();
            if (!fileName.startsWith("http") && !fileName.startsWith("file")) {
                if (fileName.startsWith("/")) {
                    fileName = "file://" + fileName; // sous linux
                } else {
                    fileName = "file:///" + fileName; // sous windows
                }
            }
            try {
                BufferedImage img = ImageIO.read(new URL(fileName));
                entity.BackgroundImage = img;
                entity._str_image_deco = openFileDialog1.getSelectedFile().getName();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
    }//GEN-LAST:event_button_imgMouseClicked

    private void buttonCouleurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCouleurMouseClicked
        // TODO add your handling code here:

        // List des couleurs deja existantes
        ArrayList<Color> lst_col = new ArrayList<Color>();
        for (int ii = 0; ii < entities.size(); ii++) {
            lst_col.add((entities.get(ii)).Couleur);
        }
        JColorChooser colorDialog1 = new JColorChooser();
        colorDialog1.setColor(entity.Couleur);

        Color returnColor = JColorChooser.showDialog(this, "", entity.Couleur);
        {
            lst_col.remove(_old_color);

            if (lst_col.contains(returnColor) == true) {
                // Cas ou la couleur existe deja
                if (Lang.getInstance().getLang().equals("FR")) {
                    JOptionPane.showMessageDialog(this, "Cette couleur d'entite existe deja. Changement non effectue.", "Information", JOptionPane.INFORMATION_MESSAGE, null);
                } else {
                    JOptionPane.showMessageDialog(this, "This color is already in use", "Information", JOptionPane.INFORMATION_MESSAGE, null);
                }

            } else {
                if (returnColor != null) {
                    if (returnColor.equals(Color.WHITE)) {
                        // Cas ou la couleur est blanche
                        if (Lang.getInstance().getLang().equals("FR")) {
                            JOptionPane.showMessageDialog(this, "La couleur blanche est reservee pour les emplacements vides de l'environnement. Changement non effectue.", "Information", JOptionPane.INFORMATION_MESSAGE, null);
                        } else {
                            JOptionPane.showMessageDialog(this, "The white color is not usable.", "Information", JOptionPane.INFORMATION_MESSAGE, null);
                        }

                    } else {
                        entity.Couleur = returnColor;
                        buttonCouleur.setBackground(returnColor);
                        _old_color = entity.Couleur;
                    }
                }
            }
        }
    }//GEN-LAST:event_buttonCouleurMouseClicked

    private void button_OKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_OKMouseClicked


    }//GEN-LAST:event_button_OKMouseClicked

    private void textBox1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBox1KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (c == '\\' || c == '/' || c == ':' || c == ' ' || c == '*' || c == '?' || c == '\"' || c == '<' || c == '>' || c == '|') {
            evt.consume();
            if (Lang.getInstance().getLang().equals("FR")) {
                JOptionPane.showMessageDialog(this, "Les caracteres \\ / : ESPACE * ? \" < > , et | sont interdits. Merci de votre comprehension", "ATTENTION", JOptionPane.INFORMATION_MESSAGE, null);
            } else {
                JOptionPane.showMessageDialog(this, "Characteres \\ / : SPACE * ? \" < > , and | are forbiden.", "ATTENTION", JOptionPane.INFORMATION_MESSAGE, null);
            }
        }
        if (c == '\n') {
            button_OKActionPerformed(null);
        }
    }//GEN-LAST:event_textBox1KeyTyped

    private void button_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_OKActionPerformed
        if (textBox1.getText().equals("")) {
            if (Lang.getInstance().getLang().equals("FR")) {
                JOptionPane.showMessageDialog(this, "Merci de nommer l'entité.", "Information", JOptionPane.INFORMATION_MESSAGE, null);
            } else {
                JOptionPane.showMessageDialog(this, "Please name the entity.", "Information", JOptionPane.INFORMATION_MESSAGE, null);
            }
            return;
        }

        // List des noms deja existants
        ArrayList<String> lst_str = new ArrayList<>();
        for (Entity entity : entities) {
            lst_str.add(entity._etiquettes);
        }

        lst_str.remove(_old_name);

        // Verif que le nom n'est pas deja attribue a un comportement
        for (int n = 0; n < behaviours.size(); n++) {
            if (behaviours.get(n).TrouveEtiquette(textBox1.getText()) >= 0) {
                // Cas ou le nom existe deja
                if (Lang.getInstance().getLang().equals("FR")) {
                    JOptionPane.showMessageDialog(this, "Ce nom existe déjà. Veuillez en changer svp.", "Information", JOptionPane.INFORMATION_MESSAGE, null);
                } else {
                    JOptionPane.showMessageDialog(this, "This name already exists.", "Information", JOptionPane.INFORMATION_MESSAGE, null);
                }
                return;
            }
        }

        if (lst_str.contains(textBox1.getText()) == true) {
            // Cas ou le nom existe deja
            if (Lang.getInstance().getLang().equals("FR")) {
                JOptionPane.showMessageDialog(this, "Ce nom existe déjà. Veuillez en changer svp.", "Information", JOptionPane.INFORMATION_MESSAGE, null);
            } else {
                JOptionPane.showMessageDialog(this, "This name already exists.", "Information", JOptionPane.INFORMATION_MESSAGE, null);
            }
            return;
        }
        try {
            entity.setEtiquettes(textBox1.getText());
            entity.getDescription().setText(richTextBox_description.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        // Apparence
        entity.Couleur = buttonCouleur.getBackground();
        entity._forme = comboBox_formes.getSelectedIndex();

        // Demi-vie
        try {
            entity.DemieVie = Double.parseDouble(textBox_demie_vie.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            entity.DemieVie = 0;
        }

        // Vidable
        entity.Vidable = jCheckBox_vidable.isSelected();
        // Invisible
        entity.setInvisible(jCheckBox_invisible.isSelected());
        // Deplacement
        entity.mvt_proba = Double.parseDouble(textBox_mvt.getText());
        entity.mvt_gauche   = !jToggleButtonLeft.isSelected();
        entity.mvt_droite   = !jToggleButtonRight.isSelected();
        entity.mvt_haut     = !jToggleButtonUp.isSelected();
        entity.mvt_bas      = !jToggleButtonDown.isSelected();
        entity.mvt_devant   = !jToggleButtonFront.isSelected();
        entity.mvt_derriere = !jToggleButtonBack.isSelected();

        this.DialogResult = new String("OK");
        setVisible(false);
    }//GEN-LAST:event_button_OKActionPerformed

    private void button_CANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CANCELActionPerformed
        // TODO add your handling code here:
        this.DialogResult = new String("CANCEL");
        setVisible(false);
    }//GEN-LAST:event_button_CANCELActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:

        //if()
    }//GEN-LAST:event_formKeyPressed

    private void button_aide_descriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_aide_descriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_aide_descriptionActionPerformed

    private void jToggleButtonRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonRightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButtonRightActionPerformed

    private void jToggleButtonLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonLeftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButtonLeftActionPerformed

    private void jToggleButtonUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButtonUpActionPerformed

    private void jToggleButtonDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonDownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButtonDownActionPerformed

    private void jToggleButtonFrontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonFrontActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButtonFrontActionPerformed

    private void jToggleButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButtonBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCouleur;
    private javax.swing.JButton button_CANCEL;
    private javax.swing.JButton button_OK;
    private javax.swing.JButton button_aide_description;
    private javax.swing.JButton button_img;
    private javax.swing.JButton button_pas_image;
    private javax.swing.JComboBox comboBox_formes;
    private javax.swing.JCheckBox jCheckBox_invisible;
    private javax.swing.JCheckBox jCheckBox_vidable;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabelApp;
    private javax.swing.JLabel jLabelDescr;
    private javax.swing.JLabel jLabelEntite;
    private javax.swing.JLabel jLabelNom;
    private javax.swing.JLabel jLabelVie;
    private javax.swing.JLabel jLabelVie1;
    private javax.swing.JLabel jLabelVie2;
    private javax.swing.JLabel jLabelVie3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JToggleButton jToggleButtonBack;
    private javax.swing.JToggleButton jToggleButtonDown;
    private javax.swing.JToggleButton jToggleButtonFront;
    private javax.swing.JToggleButton jToggleButtonLeft;
    private javax.swing.JToggleButton jToggleButtonRight;
    private javax.swing.JToggleButton jToggleButtonUp;
    private javax.swing.JTextArea richTextBox_description;
    private javax.swing.JTextField textBox1;
    private javax.swing.JTextField textBox_demie_vie;
    private javax.swing.JTextField textBox_mvt;
    // End of variables declaration//GEN-END:variables

}
