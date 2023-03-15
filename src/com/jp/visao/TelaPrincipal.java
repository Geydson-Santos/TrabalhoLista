/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jp.visao;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.lang.invoke.MethodHandles;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import listas.EmptyListException;
import listas.Lista;

/**
 *
 * @author aluno
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    Lista lista = null;
    
    enum Inserir {
        INICIO, FIM, POSICAO
    }
    
    enum Remover {
        INICIO, FIM, VALOR, POSICAO
    }
    
    public TelaPrincipal() {
        String nomeDaLista = JOptionPane.showInputDialog("Insira o nome da Lista:");
        if(!nomeDaLista.equals("")) lista = new Lista(nomeDaLista);
        else lista = new Lista();
        
        initComponents();
        this.setLocationRelativeTo(null);
        adicionarTela(jInternalFrameTelaInicial);
        ImageIcon imagem = new ImageIcon("./src/com/jp/icones/Chevron Left.png");
        imagem.setImage(imagem.getImage().getScaledInstance(25, 25, Image.SCALE_REPLICATE));
        jButtonVoltar.setIcon(imagem);
        jButtonVoltar2.setIcon(imagem);
    }
    
    private void adicionarTela(JInternalFrame tela){
        tela.setSize(jDesktopPane1.getWidth(), jDesktopPane1.getHeight());
        ((BasicInternalFrameUI)tela.getUI()).setNorthPane(null);
        jDesktopPane1.removeAll();
        jDesktopPane1.add(tela);
        tela.setVisible(true);
    }
    
    public void inserir(Inserir inserir){
        String inputNumero = "";
        String inputPosicao = "";
        
        boolean eNumero = false;
        boolean passou = false;
        
        int numero = 0;
        while(!eNumero && inputNumero != null && inputPosicao != null){
            if(!passou) inputNumero = JOptionPane.showInputDialog("Digite o elemento a ser inserido:");
            if(inputNumero != null && !inputNumero.equals("")){
                try{
                    if(!passou) numero = Integer.parseInt(inputNumero);
                    passou = true;
                    switch (inserir) {
                        case INICIO:
                            lista.insereNoInicio(numero);
                            JOptionPane.showMessageDialog(null, String.format("Número %d inserido!", numero));
                            eNumero = true;
                            break;
                        case FIM:
                            lista.insereNoFim(numero);
                            JOptionPane.showMessageDialog(null, String.format("Número %d inserido!", numero));
                            eNumero = true;
                            break;
                        case POSICAO:
                            boolean ePosicao = false;
                            while(!ePosicao && (inputPosicao = JOptionPane.showInputDialog(String.format("Informe a posição do elemento %d.", numero))) != null){
                                //inputPosicao = JOptionPane.showInputDialog("Digite o elemento a ser inserido:");
                                
                                int posicao = Integer.parseInt(inputPosicao);
                                
                                lista.insertAtPosicao(posicao, numero);
                                JOptionPane.showMessageDialog(null, String.format("Número %d inserido na posição %d!", numero, posicao));
                                
                                ePosicao = true;
                            }
                            eNumero = true;
                            break;
                    }
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null, "Por favor, informe um número.");
                }
            }
        }
        
    }
    
    public void remover(Remover remover){
        switch (remover) {
            case INICIO:
                try {
                    int numero = Integer.parseInt(lista.removeNoInicio().toString());
                    JOptionPane.showMessageDialog(null, String.format("O número %d foi removida da lista %s", numero, lista.getNome()));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }catch(EmptyListException ele){
                    JOptionPane.showMessageDialog(null, ele.getMessage());
                }
                break;
            case FIM:
                try {
                    int numero = Integer.parseInt(lista.removeNoFim().toString());
                    JOptionPane.showMessageDialog(null, String.format("O número %d foi removida da lista %s", numero, lista.getNome()));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }catch(EmptyListException ele){
                    JOptionPane.showMessageDialog(null, ele.getMessage());
                }
                
                break;
            case VALOR:
                boolean eNumero = false;
                String input = "";
                while(!eNumero && input != null){
                    try {
                        input = JOptionPane.showInputDialog("Digite o número a ser removido:");
                        if(input != null){
                            int valor = Integer.parseInt(input);

                            String saida = String.format("O número %d foi removido da lista %s!", valor, lista.getNome());
                            if(lista.removeFromValor(valor) != null) JOptionPane.showMessageDialog(null, saida);
                            else JOptionPane.showMessageDialog(null, saida.replace("foi removido da", "não foi encontrado na"));
                            eNumero = true;
                        }
                        
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor, informe um número.");
                    }catch(EmptyListException ele){
                        JOptionPane.showMessageDialog(null, ele.getMessage());
                    }
                }
                
                break;
            case POSICAO:
                boolean ePosicao = false;
                input = "";
                while(!ePosicao && input != null){
                    try {
                        input = JOptionPane.showInputDialog("Digite a posição do número a ser removido:");
                        if(input != null){
                            int posicao = Integer.parseInt(input);
                            
                            Object objeto = lista.removeFromPosicao(posicao);
                            
                            if(objeto != null) {
                                int valor = Integer.parseInt(objeto.toString());
                                String saida = String.format("O número %d foi removido da lista %s!", valor, lista.getNome());
                                JOptionPane.showMessageDialog(null, saida);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, String.format("A posição %d da lista %s não foi encontrada.", posicao, lista.getNome()));
                            }
                            ePosicao = true;
                        }
                        
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor, informe um número.");
                    }catch(EmptyListException ele){
                        JOptionPane.showMessageDialog(null, ele.getMessage());
                    }
                }
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrameTelaInicial = new javax.swing.JInternalFrame();
        jButtonInserir = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jInternalFrameInserir = new javax.swing.JInternalFrame();
        jButtonInserirInicio = new javax.swing.JButton();
        jButtonInserirFim = new javax.swing.JButton();
        jButtonInserirPorPosicao = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jInternalFrameRemover = new javax.swing.JInternalFrame();
        jButtonRemoverInicio = new javax.swing.JButton();
        jButtonRemoverFim = new javax.swing.JButton();
        jButtonRemoverPorValor = new javax.swing.JButton();
        jButtonRemoverPorPosicao = new javax.swing.JButton();
        jButtonVoltar2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        jInternalFrameTelaInicial.setBorder(null);
        jInternalFrameTelaInicial.setPreferredSize(new java.awt.Dimension(611, 326));
        jInternalFrameTelaInicial.setVisible(true);

        jButtonInserir.setBackground(new java.awt.Color(204, 204, 204));
        jButtonInserir.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButtonInserir.setForeground(new java.awt.Color(51, 51, 51));
        jButtonInserir.setText("Inserir Novo Elemento");
        jButtonInserir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonInserir.setFocusable(false);
        jButtonInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInserirActionPerformed(evt);
            }
        });

        jButtonRemover.setBackground(new java.awt.Color(204, 204, 204));
        jButtonRemover.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButtonRemover.setForeground(new java.awt.Color(51, 51, 51));
        jButtonRemover.setText("Remover Elemento");
        jButtonRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRemover.setFocusable(false);
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        jButtonBuscar.setBackground(new java.awt.Color(204, 204, 204));
        jButtonBuscar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButtonBuscar.setForeground(new java.awt.Color(51, 51, 51));
        jButtonBuscar.setText("Buscar Elemento");
        jButtonBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonBuscar.setFocusable(false);
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jButtonImprimir.setBackground(new java.awt.Color(204, 204, 204));
        jButtonImprimir.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButtonImprimir.setForeground(new java.awt.Color(51, 51, 51));
        jButtonImprimir.setText("Imprimir Elementos");
        jButtonImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonImprimir.setFocusable(false);
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrameTelaInicialLayout = new javax.swing.GroupLayout(jInternalFrameTelaInicial.getContentPane());
        jInternalFrameTelaInicial.getContentPane().setLayout(jInternalFrameTelaInicialLayout);
        jInternalFrameTelaInicialLayout.setHorizontalGroup(
            jInternalFrameTelaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameTelaInicialLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jInternalFrameTelaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonInserir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jInternalFrameTelaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jInternalFrameTelaInicialLayout.setVerticalGroup(
            jInternalFrameTelaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrameTelaInicialLayout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addGroup(jInternalFrameTelaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jInternalFrameTelaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );

        jInternalFrameInserir.setBorder(null);
        jInternalFrameInserir.setPreferredSize(new java.awt.Dimension(611, 326));
        jInternalFrameInserir.setVisible(true);

        jButtonInserirInicio.setBackground(new java.awt.Color(204, 204, 204));
        jButtonInserirInicio.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButtonInserirInicio.setForeground(new java.awt.Color(51, 51, 51));
        jButtonInserirInicio.setText("Inserir no Início");
        jButtonInserirInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonInserirInicio.setFocusable(false);
        jButtonInserirInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInserirInicioActionPerformed(evt);
            }
        });

        jButtonInserirFim.setBackground(new java.awt.Color(204, 204, 204));
        jButtonInserirFim.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButtonInserirFim.setForeground(new java.awt.Color(51, 51, 51));
        jButtonInserirFim.setText("Inserir no Fim");
        jButtonInserirFim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonInserirFim.setFocusable(false);
        jButtonInserirFim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInserirFimActionPerformed(evt);
            }
        });

        jButtonInserirPorPosicao.setBackground(new java.awt.Color(204, 204, 204));
        jButtonInserirPorPosicao.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButtonInserirPorPosicao.setForeground(new java.awt.Color(51, 51, 51));
        jButtonInserirPorPosicao.setText("Inserir por Posição");
        jButtonInserirPorPosicao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonInserirPorPosicao.setFocusable(false);
        jButtonInserirPorPosicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInserirPorPosicaoActionPerformed(evt);
            }
        });

        jButtonVoltar.setBackground(new java.awt.Color(0, 102, 102));
        jButtonVoltar.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButtonVoltar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVoltar.setText("Voltar");
        jButtonVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonVoltar.setFocusable(false);
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Inserir Novo Elemento");

        javax.swing.GroupLayout jInternalFrameInserirLayout = new javax.swing.GroupLayout(jInternalFrameInserir.getContentPane());
        jInternalFrameInserir.getContentPane().setLayout(jInternalFrameInserirLayout);
        jInternalFrameInserirLayout.setHorizontalGroup(
            jInternalFrameInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameInserirLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jInternalFrameInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrameInserirLayout.createSequentialGroup()
                        .addComponent(jButtonVoltar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jInternalFrameInserirLayout.createSequentialGroup()
                        .addComponent(jButtonInserirInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonInserirFim, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jButtonInserirPorPosicao, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addGap(27, 27, 27))))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrameInserirLayout.setVerticalGroup(
            jInternalFrameInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameInserirLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jButtonVoltar)
                .addGap(58, 58, 58)
                .addGroup(jInternalFrameInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInserirPorPosicao, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonInserirInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonInserirFim, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jInternalFrameRemover.setBorder(null);
        jInternalFrameRemover.setVisible(true);

        jButtonRemoverInicio.setBackground(new java.awt.Color(204, 204, 204));
        jButtonRemoverInicio.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButtonRemoverInicio.setForeground(new java.awt.Color(51, 51, 51));
        jButtonRemoverInicio.setText("Remover no Início");
        jButtonRemoverInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRemoverInicio.setFocusable(false);
        jButtonRemoverInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverInicioActionPerformed(evt);
            }
        });

        jButtonRemoverFim.setBackground(new java.awt.Color(204, 204, 204));
        jButtonRemoverFim.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButtonRemoverFim.setForeground(new java.awt.Color(51, 51, 51));
        jButtonRemoverFim.setText("Remover no Fim");
        jButtonRemoverFim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRemoverFim.setFocusable(false);
        jButtonRemoverFim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverFimActionPerformed(evt);
            }
        });

        jButtonRemoverPorValor.setBackground(new java.awt.Color(204, 204, 204));
        jButtonRemoverPorValor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButtonRemoverPorValor.setForeground(new java.awt.Color(51, 51, 51));
        jButtonRemoverPorValor.setText("Remover por Valor");
        jButtonRemoverPorValor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRemoverPorValor.setFocusable(false);
        jButtonRemoverPorValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverPorValorActionPerformed(evt);
            }
        });

        jButtonRemoverPorPosicao.setBackground(new java.awt.Color(204, 204, 204));
        jButtonRemoverPorPosicao.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButtonRemoverPorPosicao.setForeground(new java.awt.Color(51, 51, 51));
        jButtonRemoverPorPosicao.setText("Remover por Posição");
        jButtonRemoverPorPosicao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRemoverPorPosicao.setFocusable(false);
        jButtonRemoverPorPosicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverPorPosicaoActionPerformed(evt);
            }
        });

        jButtonVoltar2.setBackground(new java.awt.Color(0, 102, 102));
        jButtonVoltar2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButtonVoltar2.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVoltar2.setText("Voltar");
        jButtonVoltar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonVoltar2.setFocusable(false);
        jButtonVoltar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltar2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Remover Elemento");

        javax.swing.GroupLayout jInternalFrameRemoverLayout = new javax.swing.GroupLayout(jInternalFrameRemover.getContentPane());
        jInternalFrameRemover.getContentPane().setLayout(jInternalFrameRemoverLayout);
        jInternalFrameRemoverLayout.setHorizontalGroup(
            jInternalFrameRemoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameRemoverLayout.createSequentialGroup()
                .addGroup(jInternalFrameRemoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrameRemoverLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jInternalFrameRemoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonRemoverInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonRemoverPorValor, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(jInternalFrameRemoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonRemoverFim, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRemoverPorPosicao, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jInternalFrameRemoverLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButtonVoltar2)))
                .addContainerGap(124, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrameRemoverLayout.setVerticalGroup(
            jInternalFrameRemoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrameRemoverLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonVoltar2)
                .addGap(18, 18, 18)
                .addGroup(jInternalFrameRemoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRemoverInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRemoverFim, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jInternalFrameRemoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRemoverPorValor, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRemoverPorPosicao, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Trabalho sobre Listas, pilha e fila");

        jDesktopPane1.setPreferredSize(new java.awt.Dimension(611, 326));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 611, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserirActionPerformed
        // TODO add your handling code here:
        adicionarTela(jInternalFrameInserir);
    }//GEN-LAST:event_jButtonInserirActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        // TODO add your handling code here:
        adicionarTela(jInternalFrameTelaInicial);
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButtonVoltar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltar2ActionPerformed
        // TODO add your handling code here:
        adicionarTela(jInternalFrameTelaInicial);
    }//GEN-LAST:event_jButtonVoltar2ActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        // TODO add your handling code here:
        adicionarTela(jInternalFrameRemover);
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        // TODO add your handling code here:
        String input = JOptionPane.showInputDialog("Digite o elemento a ser buscado:");
        if(input != null && !input.equals("")){
            try{
                int numero = Integer.parseInt(input);
                if(lista.buscaElemento(numero)) JOptionPane.showMessageDialog(null, String.format("O número %d foi encontrado na lista %s!", numero, lista.getNome()));
                else JOptionPane.showMessageDialog(null, String.format("O número %d não foi encontrado na lista %s!", numero, lista.getNome()));
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "Por favor, informe um número.");
                jButtonBuscar.doClick();
            }catch(EmptyListException ele){
                JOptionPane.showMessageDialog(null, ele.getMessage());
            }
        }
        
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, lista.print());
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonInserirInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserirInicioActionPerformed
        // TODO add your handling code here:
        inserir(Inserir.INICIO);
    }//GEN-LAST:event_jButtonInserirInicioActionPerformed

    private void jButtonInserirFimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserirFimActionPerformed
        // TODO add your handling code here:
        inserir(Inserir.FIM);
    }//GEN-LAST:event_jButtonInserirFimActionPerformed

    private void jButtonInserirPorPosicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserirPorPosicaoActionPerformed
        // TODO add your handling code here:
        inserir(Inserir.POSICAO);
    }//GEN-LAST:event_jButtonInserirPorPosicaoActionPerformed

    private void jButtonRemoverInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverInicioActionPerformed
        // TODO add your handling code here:
        remover(Remover.INICIO);
    }//GEN-LAST:event_jButtonRemoverInicioActionPerformed

    private void jButtonRemoverFimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverFimActionPerformed
        // TODO add your handling code here:
        remover(Remover.FIM);
    }//GEN-LAST:event_jButtonRemoverFimActionPerformed

    private void jButtonRemoverPorValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverPorValorActionPerformed
        // TODO add your handling code here:
        remover(Remover.VALOR);
    }//GEN-LAST:event_jButtonRemoverPorValorActionPerformed

    private void jButtonRemoverPorPosicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverPorPosicaoActionPerformed
        // TODO add your handling code here:
        remover(Remover.POSICAO);
    }//GEN-LAST:event_jButtonRemoverPorPosicaoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        if(!FlatDarkLaf.setup()) JOptionPane.showMessageDialog(null, "Falha ao importar o tema.");
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonInserir;
    private javax.swing.JButton jButtonInserirFim;
    private javax.swing.JButton jButtonInserirInicio;
    private javax.swing.JButton jButtonInserirPorPosicao;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JButton jButtonRemoverFim;
    private javax.swing.JButton jButtonRemoverInicio;
    private javax.swing.JButton jButtonRemoverPorPosicao;
    private javax.swing.JButton jButtonRemoverPorValor;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JButton jButtonVoltar2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JInternalFrame jInternalFrameInserir;
    private javax.swing.JInternalFrame jInternalFrameRemover;
    private javax.swing.JInternalFrame jInternalFrameTelaInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
