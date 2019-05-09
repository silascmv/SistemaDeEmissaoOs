/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;
    
    import java.sql.*;
    import br.com.infox.dal.ModuloConexao;
import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;




/**
 *
 * @author $$$
 */
public class TelaCadastroUsuarios extends javax.swing.JInternalFrame {

         Connection conexao = null;
         PreparedStatement pst = null;
         ResultSet rs = null;
        
    
    
    /**
     * Creates new form TelaCadastroUsuarios
     */
        public TelaCadastroUsuarios() {
        initComponents();
        conexao = ModuloConexao.conector();
        
        }
   
        private void consultar() throws SQLException{
                String sql = "select * from usuarios where id_usu=?";
                try {
                    
                       
                 pst = conexao.prepareStatement(sql);
                 pst.setString(1, txtID.getText());
                 rs = pst.executeQuery();
                
                 if(rs.next()){
                        
                        String nomeUsuario = rs.getString(2);
                        String telefoneUsuario = rs.getString(3);
                        String loginUsuario = rs.getString(4);
                        String senhaUsuario = rs.getString(5);
                        String perfilUsuario = rs.getString(6);
                        
                        
                        
                        txtNomeUsuario.setText(nomeUsuario); 
                        txtTelefoneUsuario.setText(telefoneUsuario);
                        txtLoginUsuario.setText(loginUsuario);
                        txtSenhaUsuario.setText(senhaUsuario);
                        cbPerfil.setSelectedItem(perfilUsuario);
                        
                 }else {
                 
                         JOptionPane.showMessageDialog(null,"Usuario Não encontrado");
                        
                        txtNomeUsuario.setText(null);
                        txtTelefoneUsuario.setText(null);
                        txtLoginUsuario.setText(null);
                        txtSenhaUsuario.setText(null);
                        cbPerfil.setSelectedItem(null);
                 }
                 
                 
                    } catch (HeadlessException | SQLException e) {
                
                            
                        JOptionPane.showMessageDialog(null,"Erro na conexão com o banco de dados" + e);
                       
                    
                                    }
                
                 
                 
                // a linha abaixo executa a query
                
            
            
                };
        
        
        public void adicionar (){
        
        
                    String sqlAdicionar = "insert into usuarios (id_usu,nome,fone,login,senha,perfil) values(?,?,?,?,?,?)";
                     
                                             
                    
                        try {
                
                                
                            
                              pst = conexao.prepareStatement(sqlAdicionar);
                              pst.setString(1, txtID.getText());
                              pst.setString(2, txtNomeUsuario.getText());
                              pst.setString(3, txtTelefoneUsuario.getText());
                              pst.setString(4, txtLoginUsuario.getText());
                              pst.setString(5, txtSenhaUsuario.getText());
                              pst.setString(6, (String) cbPerfil.getSelectedItem());
                              
                              
                              if (txtID.getText().isEmpty()||txtNomeUsuario.getText().isEmpty()||txtLoginUsuario.getText().isEmpty()||txtSenhaUsuario.getText().isEmpty()) {
                                  
                                    JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios");
                                    
                                
                            } else {
                              
                              
                        // a linha abaixo atualiza de suarios com os dados do formulario
                        //a estrutura abaixo é usada para confirmar a inserção dos dados na tabela.
                              int adicionado = pst.executeUpdate();
                              
                                if(adicionado > 0){
                                
                                JOptionPane.showMessageDialog(null, "Usuario Adicionado com sucesso");
                                txtID.setText(null);
                                txtNomeUsuario.setText(null);
                                txtTelefoneUsuario.setText(null);
                                txtLoginUsuario.setText(null);
                                txtSenhaUsuario.setText(null);
                                                                }
                                               
                                
                                
                                
                                }
                                }
                             
                        
                        
                        catch (SQLException e) {
                            
                            
                          JOptionPane.showMessageDialog(null,"teste" + e);
                   
                        }
        
        };
         
        
        
        
        private void alterar(){
        
            String sqlAlterar = "update usuarios set nome=?,fone=?,login=?,senha=?,perfil=? where id_usu=? ";
            
            
              
                
                try {
                    
                    pst=conexao.prepareStatement(sqlAlterar);
                  
                    pst.setString(1, txtNomeUsuario.getText());
                    pst.setString(2,txtTelefoneUsuario.getText());
                    pst.setString(3,txtLoginUsuario.getText());
                    pst.setString(4, txtSenhaUsuario.getText());
                    pst.setString(5, (String) cbPerfil.getSelectedItem());
                    pst.setString(6,txtID.getText());
                    
                    if (txtID.getText().isEmpty()||txtNomeUsuario.getText().isEmpty()||txtLoginUsuario.getText().isEmpty()||txtSenhaUsuario.getText().isEmpty()) {
                                  
                                    JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios");
                                    
                                
                            } else {
                              
                              
                        // a linha abaixo atualiza de suarios com os dados do formulario
                        //a estrutura abaixo é usada para confirmar a inserção dos dados na tabela.
                              int adicionado = pst.executeUpdate();
                              
                                if(adicionado > 0){
                                
                                JOptionPane.showMessageDialog(null, "Usuario Alterado com sucesso");
                                txtID.setText(null);
                                txtNomeUsuario.setText(null);
                                txtTelefoneUsuario.setText(null);
                                txtLoginUsuario.setText(null);
                                txtSenhaUsuario.setText(null);
                                                                }
                                               
                                
                                
                                
                                }
                    
                    
                    
                         int executeUpdate = pst.executeUpdate();
                    
                        

                        } catch (SQLException  e) {
                            
                            JOptionPane.showMessageDialog(null,e);
                       
                        }
                
        
        }
        
        
        private void remover(){
            
        
                int confirma = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja excluir esse usuário?","Atenção!", JOptionPane.YES_NO_OPTION);
                
                    if(confirma == JOptionPane.YES_OPTION){
                    
                    
                            String sqlRemover = "delete from usuarios where id_usu=?";
                            
                            try {
                                
                                pst=conexao.prepareStatement(sqlRemover);
                                pst.setString(1,txtID.getText());
                                
                                int apagado = pst.executeUpdate();
                                
                                if(apagado > 0){
                                
                                    JOptionPane.showMessageDialog(null, "Usuario removido com sucesso.");
                                    
                                txtID.setText(null);
                                txtNomeUsuario.setText(null);
                                txtTelefoneUsuario.setText(null);
                                txtLoginUsuario.setText(null);
                                txtSenhaUsuario.setText(null);
                            
                                }
                                
                        } catch (SQLException e) {
                            
                             JOptionPane.showMessageDialog(null,e);
                            
                        }
                        
                    
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

        jPanel1 = new javax.swing.JPanel();
        btnApagar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnProcurar = new javax.swing.JButton();
        btnInserir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNomeUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtLoginUsuario = new javax.swing.JTextField();
        txtSenhaUsuario = new javax.swing.JTextField();
        txtTelefoneUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbPerfil = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuarios");
        setPreferredSize(new java.awt.Dimension(1336, 500));

        btnApagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/if_edit-delete_118920 (1).png"))); // NOI18N
        btnApagar.setToolTipText("Apagar Usuário");
        btnApagar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnApagar.setPreferredSize(new java.awt.Dimension(48, 48));
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/if_3floppy_unmount_18268.png"))); // NOI18N
        btnEditar.setToolTipText("Atualizar");
        btnEditar.setPreferredSize(new java.awt.Dimension(48, 48));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnProcurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/if_xmag_3617.png"))); // NOI18N
        btnProcurar.setPreferredSize(new java.awt.Dimension(48, 48));
        btnProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcurarActionPerformed(evt);
            }
        });

        btnInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/if_contact-new_9230.png"))); // NOI18N
        btnInserir.setToolTipText("Adicionar");
        btnInserir.setPreferredSize(new java.awt.Dimension(50, 50));
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Cadastro de Usuários");

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jLabel2.setText("* ID:");

        jLabel1.setText("* Nome:");

        txtNomeUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeUsuarioActionPerformed(evt);
            }
        });

        jLabel4.setText("* Login");

        txtSenhaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaUsuarioActionPerformed(evt);
            }
        });

        jLabel3.setText("* Telefone: ");

        jLabel6.setText("* Perfil");

        cbPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "atendimento", "DBA" }));
        cbPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPerfilActionPerformed(evt);
            }
        });

        jLabel5.setText("* Senha");

        jLabel8.setText("* Campos Obrigatórios");

        jLabel9.setText("Pesquisar");

        jLabel10.setText("Adicionar");

        jLabel11.setText("Salvar");

        jLabel12.setText("Apagar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(349, 349, 349)
                .addComponent(jLabel7)
                .addGap(0, 369, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(btnProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(jLabel6)
                .addGap(26, 26, 26)
                .addComponent(cbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4))
                            .addGap(28, 28, 28))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtLoginUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTelefoneUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addGap(32, 32, 32)
                            .addComponent(txtSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(73, 73, 73))
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(199, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnProcurar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnInserir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(22, 22, 22))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(22, 22, 22)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(24, 24, 24)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(txtLoginUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(25, 25, 25)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtTelefoneUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(127, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(185, 185, 185))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1336, 500);
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtNomeUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeUsuarioActionPerformed

    private void cbPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPerfilActionPerformed

    private void txtSenhaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaUsuarioActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
             
           
                 alterar();
           
            
        
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcurarActionPerformed
             try {
                 // TODO add your handling code here:

                 consultar();
             } catch (SQLException ex) {
                 Logger.getLogger(TelaCadastroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
             }
    }//GEN-LAST:event_btnProcurarActionPerformed

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
          
             
                 adicionar();
             
                 
             
    }//GEN-LAST:event_btnInserirActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        // TODO add your handling code here:
        
        
        
        remover();
        
        
        
    }//GEN-LAST:event_btnApagarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnInserir;
    private javax.swing.JButton btnProcurar;
    private javax.swing.JComboBox<String> cbPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLoginUsuario;
    private javax.swing.JTextField txtNomeUsuario;
    private javax.swing.JTextField txtSenhaUsuario;
    private javax.swing.JTextField txtTelefoneUsuario;
    // End of variables declaration//GEN-END:variables
}
