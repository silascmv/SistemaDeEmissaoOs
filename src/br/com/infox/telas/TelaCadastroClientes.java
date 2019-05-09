/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import br.com.infox.dal.ModuloConexao;
import java.awt.HeadlessException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author $$$
 */
public class TelaCadastroClientes extends javax.swing.JInternalFrame {
    
    
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
            
    

    public TelaCadastroClientes() {
        initComponents();
        
        con = ModuloConexao.conector();
        
            }
    
    
    public void limpar_conteudo(){
    
                                txtContato.setText(null);
                                txtEmailCliente.setText(null);
                                txtEnderecoCliente.setText(null);
                                txtNomeCliente.setText(null);
                                txtCPF.setText(null);
        
    };
    
               
        public void adicionarCliente(){
        
        
                    String sqlAdicionar = "insert into clientes(nome_cliente,end_cliente,fone_cliente,email_cliente,cpf) values(?,?,?,?,?)";
                     
                                              
                        try {
                
                                                           
                              pst = con.prepareStatement(sqlAdicionar);
                              pst.setString(1, txtNomeCliente.getText());
                              pst.setString(2, txtEnderecoCliente.getText());
                              pst.setString(3, txtContato.getText());
                              pst.setString(4, txtEmailCliente.getText());
                              pst.setString(5, txtCPF.getText());
                              
                              if (txtNomeCliente.getText().isEmpty()||txtEnderecoCliente.getText().isEmpty()||txtEmailCliente.getText().isEmpty()||txtCPF.getText().isEmpty()) {
                                  
                                    JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios");
                                    
                                
                            } else {
                              
                              
                        // a linha abaixo adiciona os clientes com os dados do formulario
                        //a estrutura abaixo é usada para confirmar a inserção dos dados na tabela.
                                int adicionado = pst.executeUpdate();
                              
                                if(adicionado > 0){
                                
                                JOptionPane.showMessageDialog(null, "Usuario Adicionado com sucesso");
                                txtContato.setText(null);
                                txtEmailCliente.setText(null);
                                txtEnderecoCliente.setText(null);
                                txtNomeCliente.setText(null);
                                txtCPF.setText(null);
                                
                                }
                                               
                                
                                
                                
                                }
                                }
                             
                        
                        
                        catch (SQLException e) {
                            
                            
                          JOptionPane.showMessageDialog(null,"teste" + e);
                   
                        }
        
        };
        
   
        public void removerCliente() throws SQLException{
        
            
            String sqlRemover = "delete from clientes where cpf = ?";
        
            
                pst=con.prepareStatement(sqlRemover);
           
                pst.setString(1, txtCPF.getText());
            
                
                    int apagado = pst.executeUpdate();
                
                        if (apagado > 0){
                            JOptionPane.showMessageDialog(null,"Usuário Removido com Sucesso");
                        
                        
                            txtContato.setText(null);
                            txtEmailCliente.setText(null);
                            txtNomeCliente.setText(null);
                            txtContato.setText(null);
                            txtEnderecoCliente.setText(null);
                            txtCPF.setText(null);
                            txtPesquisarCliente.setText(null);
                            }else {
                                    
                                JOptionPane.showMessageDialog(null,"Usuário Não Encontrado");
                                
                        
                                    }
                            ;
            
        
        
        
        };
        
        
         private void consultar() {

        String sql = "select * from clientes where nome_cliente like ? order by id_cliente asc";
        
        try {
            
            pst = con.prepareStatement(sql);
            //
            
            pst.setString(1, "%"+txtPesquisarCliente.getText() + "%");
            rst = pst.executeQuery();
            
            tblPesquisar.setModel(DbUtils.resultSetToTableModel(rst));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    ;
         
         
         public void setar_campos(){
         
         
               int setar = tblPesquisar.getSelectedRow();
               txtNomeCliente.setText(tblPesquisar.getModel().getValueAt(setar, 1).toString());
               txtEnderecoCliente.setText(tblPesquisar.getModel().getValueAt(setar,2).toString());
               txtContato.setText(tblPesquisar.getModel().getValueAt(setar, 3).toString());
               txtEmailCliente.setText(tblPesquisar.getModel().getValueAt(setar, 4).toString());
               txtCPF.setText(tblPesquisar.getModel().getValueAt(setar, 5).toString());
         
         };
         
    
    
//        private void consultar() throws SQLException{
//                String sql = "select * from clientes where cpf = ?";
//                try {
//                    
//                       
//                 pst = con.prepareStatement(sql);
//                 pst.setString(1, txtPesquisarCliente.getText());
//                 rst = pst.executeQuery();
//                
//                 if(rst.next()){
//                        String idCliente = rst.getString(1);
//                        String nomeCliente = rst.getString(2);
//                        String endCliente = rst.getString(3);
//                        String foneCliente = rst.getString(4);
//                        String emailCliente = rst.getString(5);
//                        String cpfCliente = rst.getString(6);
//                        
//                        
//                        lblID.setText(idCliente);
//                        txtNomeCliente.setText(nomeCliente); 
//                        txtEnderecoCliente.setText(endCliente);
//                        txtContato.setText(foneCliente);
//                        txtEmailCliente.setText(emailCliente);
//                        txtCPF.setText(cpfCliente);
//                 }else {
//                 
//                         JOptionPane.showMessageDialog(null,"Usuario Não encontrado");
//                        
//                        txtCPF.setText(null);
//                        txtContato.setText(null);
//                        txtEmailCliente.setText(null);
//                        txtNomeCliente.setText(null);
//                        txtEnderecoCliente.setText(null);
//                        
//                 }
//                 
//                 
//                    } catch (HeadlessException | SQLException e) {
//                
//                            
//                        JOptionPane.showMessageDialog(null,"Erro na conexão com o banco de dados" + e);
//                       
//                    
//                                    }
//                
//                 
//                 
//                // a linha abaixo executa a query
//                
//            
//            
//                };
    
    
        private void alterar(){
        
            String sqlAlterar;
            sqlAlterar = "update clientes set nome_cliente=?,end_cliente=?,fone_cliente=?,email_cliente=?,cpf=? where cpf=?";
            
                try {
                    
                    pst=con.prepareStatement(sqlAlterar);
                  
                    pst.setString(1, txtNomeCliente.getText());
                    pst.setString(2, txtEnderecoCliente.getText());
                    pst.setString(3, txtContato.getText());
                    pst.setString(4, txtEmailCliente.getText());
                    pst.setString(5, txtCPF.getText());
                    pst.setString(6, txtCPF.getText());
                    if (txtNomeCliente.getText().isEmpty()||txtEnderecoCliente.getText().isEmpty()||txtContato.getText().isEmpty()||txtEmailCliente.getText().isEmpty()||txtCPF.getText().isEmpty()) {
                                  
                                    JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios");
                                    
                                
                            } else {
                              
                        
                              
                        // a linha abaixo atualiza de suarios com os dados do formulario
                        //a estrutura abaixo é usada para confirmar a inserção dos dados na tabela.
                              int adicionado = pst.executeUpdate();
                              
                                if(adicionado > 0){
                                
                                JOptionPane.showMessageDialog(null, "Usuario Alterado com sucesso");
                                txtNomeCliente.setText(null);
                                txtEnderecoCliente.setText(null);
                                txtContato.setText(null);
                                txtEmailCliente.setText(null);
                                txtCPF.setText(null);
                               
                                                                }
                                               
                                
                                }
                                             
                        

                        } catch (SQLException  e) {
                            
                            JOptionPane.showMessageDialog(null,e);
                       
                        }
                
        
        } 
    
    
    /**
     * Creates new form TelaCadastroClientes
     */
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnPesquisar = new javax.swing.JButton();
        txtPesquisarCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPesquisar = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnAdicionarCliente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        btnApagarCliente = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtEnderecoCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEmailCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JTextField();
        try{
            javax.swing.text.MaskFormatter cpfMascara = new javax.swing.text.MaskFormatter("###.###.###-##");

            txtCPF = new javax.swing.JFormattedTextField(cpfMascara);
        }catch(Exception e){
        }try{
            javax.swing.text.MaskFormatter cpfMascara = new javax.swing.text.MaskFormatter("###.###.###-##");

            txtCPF = new javax.swing.JFormattedTextField(cpfMascara);
        }catch(Exception e){
        }
        jLabel4 = new javax.swing.JLabel();
        txtContato = new javax.swing.JTextField();
        try{
            javax.swing.text.MaskFormatter telefone = new javax.swing.text.MaskFormatter("(##) ####-####");

            txtContato = new javax.swing.JFormattedTextField(telefone);
        }catch(Exception e){
        }try{
            javax.swing.text.MaskFormatter telefone = new javax.swing.text.MaskFormatter("(##) ####-####");

            txtContato = new javax.swing.JFormattedTextField(telefone);
        }catch(Exception e){
        }
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(1336, 500));

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/if_xmag_3617.png"))); // NOI18N
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        txtPesquisarCliente.setForeground(java.awt.SystemColor.controlDkShadow);
        txtPesquisarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPesquisarClienteMouseClicked(evt);
            }
        });
        txtPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarClienteActionPerformed(evt);
            }
        });
        txtPesquisarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisarClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarClienteKeyReleased(evt);
            }
        });

        jLabel6.setText("Pesquisar Cliente");

        tblPesquisar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Endereço", "Contato", "E-mail", "CPF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPesquisarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPesquisar);
        if (tblPesquisar.getColumnModel().getColumnCount() > 0) {
            tblPesquisar.getColumnModel().getColumn(0).setResizable(false);
            tblPesquisar.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtPesquisarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        btnAdicionarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/if_contact-new_9230.png"))); // NOI18N
        btnAdicionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarClienteActionPerformed(evt);
            }
        });

        btnEditarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/if_3floppy_unmount_18268.png"))); // NOI18N
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        btnApagarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/if_edit-delete_118920 (1).png"))); // NOI18N
        btnApagarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarClienteActionPerformed(evt);
            }
        });

        jLabel9.setText("Adicionar");

        jLabel10.setText("Salvar");

        jLabel11.setText("Apagar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnAdicionarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnEditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11)
                    .addComponent(btnApagarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionarCliente)
                    .addComponent(btnApagarCliente)
                    .addComponent(btnEditarCliente)))
        );

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Cadastro de Clientes");

        jLabel2.setText("* Nome :");

        txtNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeClienteActionPerformed(evt);
            }
        });

        jLabel1.setText("*Endereço : ");

        jLabel3.setText("* E-mail : ");

        jLabel7.setText("* CPF :");

        jLabel4.setText("Contato : ");

        txtContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContatoActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/if_broom_53859.png"))); // NOI18N
        jButton1.setText("Limpar Conteúdo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Campos Obrigatórios *");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(394, 394, 394)
                                .addComponent(jLabel8))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtContato, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(113, 113, 113)
                                        .addComponent(jButton1))
                                    .addComponent(txtEnderecoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtEnderecoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(175, 175, 175)))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1336, 500));
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeClienteActionPerformed

    private void txtContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContatoActionPerformed

    private void btnApagarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarClienteActionPerformed
        // TODO add your handling code here:
        
        int resultado = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar ?");
        
        if(resultado == JOptionPane.YES_OPTION){
        
            try {
                removerCliente();
            } catch (SQLException ex) {
                Logger.getLogger(TelaCadastroClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
        }else{
        }
        
        
        
    }//GEN-LAST:event_btnApagarClienteActionPerformed

    private void btnAdicionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarClienteActionPerformed
        // TODO add your handling code here:
        adicionarCliente();
        
        
    }//GEN-LAST:event_btnAdicionarClienteActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
       alterar();
        
        
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void txtPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarClienteActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
         
                // TODO add your handling code here:

                consultar();
          
        
        
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void txtPesquisarClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarClienteKeyPressed

    private void txtPesquisarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPesquisarClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarClienteMouseClicked

    private void tblPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPesquisarMouseClicked
        // TODO add your handling code here:
        
        setar_campos();
    }//GEN-LAST:event_tblPesquisarMouseClicked

    private void txtPesquisarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarClienteKeyReleased
        // TODO add your handling code here:
        
        consultar();
        
    }//GEN-LAST:event_txtPesquisarClienteKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        
        limpar_conteudo();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarCliente;
    private javax.swing.JButton btnApagarCliente;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblID;
    private javax.swing.JTable tblPesquisar;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtContato;
    private javax.swing.JTextField txtEmailCliente;
    private javax.swing.JTextField txtEnderecoCliente;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtPesquisarCliente;
    // End of variables declaration//GEN-END:variables
}
