/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import br.com.infox.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.infox.dal.ModuloConexao;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author $$$
 */
public class TelaCadastroOS extends javax.swing.JInternalFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rst = null;

    // A linha abaixo cria uma variavel para armazenar um texto de acordo com o radion button selecionado
    private String tipo;

    public void pesquisarClientes() {

        String sql = "select id_cliente,nome_cliente,cpf from clientes where nome_cliente like ?";

        try {

            pst = con.prepareStatement(sql);
            //

            pst.setString(1, "%" + txtPesquisaCliente.getText() + "%");
            rst = pst.executeQuery();

            tblPesquisaCliente.setModel(DbUtils.resultSetToTableModel(rst));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    ;
    
    
    private void setarCampos() {

        int setar = tblPesquisaCliente.getSelectedRow();
        txtPesquisaIDCliente.setText(tblPesquisaCliente.getModel().getValueAt(setar, 0).toString());

    }

    ;
    
    private void imprimirOsInsert(){
    
     int confirmaImpRelatorio =  JOptionPane.showConfirmDialog(null,"Deseja imprimir a O.S?","Atenção",JOptionPane.YES_NO_OPTION);
       
       if(confirmaImpRelatorio == JOptionPane.YES_OPTION){
       
           //imprimindo relatorio com framework jasper report
           
           try {
                JasperPrint impressao = JasperFillManager.fillReport("C:/Reports/OrdemDeServicoInsert.jasper",null,con);
                
          // A linha abaixo exibe o relatorio através da classe Jasper Viewer      
                
          
          JasperViewer.viewReport(impressao,false);
          
          
          
          
           } catch (JRException e) {
           
           
               JOptionPane.showMessageDialog(null, e);
               System.out.println(e);
               
              
               
           
           }
           
           
       }else{
       
       }
        
        
        
        
        
        
        
    
    };
    
    
    private void imprimirOS(){
    
        
        int confirmaImpRelatorio = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja imprimir?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if(txtIdOs.getText().isEmpty()){
        
            JOptionPane.showMessageDialog(null, "Pesquise uma O.S");
            
        }else{
        
        

        if (confirmaImpRelatorio == JOptionPane.YES_OPTION) {

            //imprimindo relatorio com framework jasper report
            try {

                //usando a classe hashmap
                HashMap filtro = new HashMap();

                filtro.put("os", Integer.parseInt(txtIdOs.getText()));

                JasperPrint impressao = JasperFillManager.fillReport("C:/Reports/OrdemDeServico.jasper", filtro, con);

                // A linha abaixo exibe o relatorio através da classe Jasper Viewer      
                JasperViewer.viewReport(impressao, false);

            } catch (JRException e) {

                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);

            }

        } else {

        }}


        
        
        
        
        
    };
    
    
    
    private void emitirOS() {

        String sql = "insert into ordem_servi (fk_id_cliente,tipo,situacao,produto,descricao,defeito,servico,tecnico_respo,valor) values(?,?,?,?,?,?,?,?,?)";

        try {

            pst = con.prepareStatement(sql);

            pst.setString(1, txtPesquisaIDCliente.getText());
            pst.setString(2, tipo);
            pst.setString(3, cboSituacao.getSelectedItem().toString());
            pst.setString(4, txtProduto.getText());
            pst.setString(5, txtDescricao.getText());
            pst.setString(6, txtDefeito.getText());
            pst.setString(7, txtServico.getText());
            pst.setString(8, txtTecnico.getText());
            pst.setString((9), txtValor.getText());

            if (txtPesquisaIDCliente.getText().isEmpty() || txtProduto.getText().isEmpty() || txtDefeito.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Preencha Todos os Campos Obrigatorios");

            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Nota emitida com Sucesso");
                }

                txtDefeito.setText(null);

                txtDescricao.setText(null);

                txtProduto.setText(null);

                txtServico.setText(null);

                txtValor.setText(null);

                txtTecnico.setText(null);
                
                imprimirOsInsert();
            }

            // VALIDAÇÃO DE CAMPOS OBRIGATORIOS.
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);

        }

    }

    ;
    
    
    private void alterarOS() {

        String sql = "update ordem_servi set tipo=?,situacao=?,produto=?,descricao=?,defeito=?,servico=?,tecnico_respo=?,valor=? where id_os=?";

        try {

            pst = con.prepareStatement(sql);

            pst.setString(1, tipo);
            pst.setString(2, cboSituacao.getSelectedItem().toString());
            pst.setString(3, txtProduto.getText());
            pst.setString(4, txtDescricao.getText());
            pst.setString(5, txtDefeito.getText());
            pst.setString(6, txtServico.getText());
            pst.setString(7, txtTecnico.getText());
            pst.setString(8, txtValor.getText());
            pst.setString(9, txtIdOs.getText());

            if (txtPesquisaIDCliente.getText().isEmpty() || txtProduto.getText().isEmpty() || txtDefeito.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Preencha Todos os Campos Obrigatorios");

            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS alterada com sucesso.");
                }

                txtDefeito.setText(null);

                txtDescricao.setText(null);

                txtProduto.setText(null);

                txtServico.setText(null);

                txtValor.setText(null);

                txtTecnico.setText(null);

                txtIdOs.setText(null);

                txtDataOS.setText(null);

                btnAdicionarOS.setEnabled(true);
                txtPesquisaCliente.setEnabled(true);
                txtPesquisaIDCliente.setText(null);

                tblPesquisaCliente.setVisible(true);

            }

            // VALIDAÇÃO DE CAMPOS OBRIGATORIOS.
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);

        }

    }

    ;
    
    private void excluirOS() {

        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir essa os ?", "Atenção", JOptionPane.YES_NO_OPTION);

        
        if (confirma == JOptionPane.YES_OPTION) {

            String sql = "delete from ordem_servi where id_os = ?";

            
            try {

                pst = con.prepareStatement(sql);

                pst.setString(1, txtIdOs.getText());

                int apagado = pst.executeUpdate();

                if (apagado > 0) {

                    JOptionPane.showMessageDialog(null, "OS exluida com sucesso!");
                    
                    txtDefeito.setText(null);

                    txtDescricao.setText(null);

                    txtProduto.setText(null);

                    txtServico.setText(null);

                    txtValor.setText(null);

                    txtTecnico.setText(null);
                    
                    txtPesquisaIDCliente.setText(null);
                    
                    txtDataOS.setText(null);
                    
                    txtIdOs.setText(null);
                                        
                    btnAdicionarOS.setEnabled(true);
                 

                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e);

            }
            
            finally{
                
                txtPesquisaCliente.setEnabled(true);
        
                tblPesquisaCliente.setVisible(true);
            
            }
            
            

        } else {

        };

               
                    
    }

    ;
    
    
    private void pesquisarOS() {
        String idos = JOptionPane.showInputDialog("Informe o número da OS");

        String sql = "select * from ordem_servi where id_os = ?";

        try {

            pst = con.prepareStatement(sql);

            pst.setString(1, idos);
            rst = pst.executeQuery();

            if (rst.next()) {
                txtIdOs.setText(rst.getString(1));
                txtPesquisaIDCliente.setText(rst.getString(2));
                txtDataOS.setText(rst.getString(3));
                //setando os radios butons
                String rbtTipo = rst.getString(4);
                if (rbtTipo.equals("Orçamento")) {
                    btnOrcamento.setSelected(true);
                    tipo = "Orçamento";
                } else {
                    btnOS.setSelected(true);
                    tipo = "Ordem de Serviço";
                }
                cboSituacao.setSelectedItem(rst.getString(5));
                txtProduto.setText(rst.getString(6));
                txtDescricao.setText(rst.getString(7));
                txtDefeito.setText(rst.getString(8));
                txtServico.setText(rst.getString(9));
                txtTecnico.setText(rst.getString(10));
                txtValor.setText(rst.getString(11));

                //desativadando algumas informações
                btnAdicionarOS.setEnabled(false);
                txtPesquisaCliente.setEnabled(false);
                tblPesquisaCliente.setVisible(false);

            } else {

                JOptionPane.showMessageDialog(null, "OS não encontrada");

            };

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }

    }

    ;
    
    /**
     * Creates new form TelaCadastroOS
     */
    public TelaCadastroOS() {
        initComponents();

        con = ModuloConexao.conector();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdOs = new javax.swing.JTextField();
        txtDataOS = new javax.swing.JTextField();
        btnOrcamento = new javax.swing.JRadioButton();
        btnOS = new javax.swing.JRadioButton();
        cboSituacao = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtPesquisaCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPesquisaCliente = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtPesquisaIDCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnDeletarOS = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnEditarOS = new javax.swing.JButton();
        btnAdicionarOS = new javax.swing.JButton();
        txtServico = new javax.swing.JTextField();
        txtTecnico = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        btnImprimir = new javax.swing.JButton();
        txtProduto = new javax.swing.JTextField();
        btnPesquisarOS = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtDefeito = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(1336, 500));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel1.setText("Nº OS");

        jLabel2.setText("Data ");

        txtIdOs.setEditable(false);

        txtDataOS.setEditable(false);

        buttonGroup1.add(btnOrcamento);
        btnOrcamento.setText("Orçamento");
        btnOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrcamentoActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnOS);
        btnOS.setText("Ordem de Serviço");
        btnOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSActionPerformed(evt);
            }
        });

        cboSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Finalizado", "Pagamento Aprovado", "Em correção", "Aguardando Peças", "Ordem de Serviço", "Entregue" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Situação : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdOs, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDataOS, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOrcamento)
                        .addGap(47, 47, 47)
                        .addComponent(btnOS)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOrcamento)
                            .addComponent(btnOS))
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addContainerGap())))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel4.setText("Cliente");

        txtPesquisaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaClienteKeyReleased(evt);
            }
        });

        tblPesquisaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "CPF"
            }
        ));
        tblPesquisaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPesquisaClienteMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPesquisaClienteMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblPesquisaCliente);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/if_search_magnifying_glass_find_103857.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtPesquisaIDCliente.setEditable(false);

        jLabel6.setText("* ID");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisaIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesquisaIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisaCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPesquisaCliente)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane2.setViewportView(txtDescricao);

        jLabel5.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Descrição");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(205, 205, 205))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        btnDeletarOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete (2).png"))); // NOI18N
        btnDeletarOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarOSActionPerformed(evt);
            }
        });

        jLabel10.setText("Técnico");

        btnEditarOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update (2).png"))); // NOI18N
        btnEditarOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarOSActionPerformed(evt);
            }
        });

        btnAdicionarOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create (2).png"))); // NOI18N
        btnAdicionarOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarOSActionPerformed(evt);
            }
        });

        jLabel8.setText("*Defeito");

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/if_Print_1493286.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnPesquisarOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/read (2).png"))); // NOI18N
        btnPesquisarOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarOSActionPerformed(evt);
            }
        });

        jLabel11.setText("Valor Total");

        jLabel7.setText("* Produto");

        jLabel9.setText("Serviço");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel8)
                                .addComponent(jLabel7))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel10)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(txtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtValor, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                                .addComponent(txtServico)
                                .addComponent(txtDefeito))
                            .addComponent(txtProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAdicionarOS, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnDeletarOS, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnPesquisarOS, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnEditarOS, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPesquisarOS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdicionarOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeletarOS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditarOS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(149, 149, 149)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1336, 500);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarOSActionPerformed
        // TODO add your handling code here:

        emitirOS();
    }//GEN-LAST:event_btnAdicionarOSActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        pesquisarClientes();


    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblPesquisaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPesquisaClienteMouseClicked
        // TODO add your handling code here:

//        setarCampos();

    }//GEN-LAST:event_tblPesquisaClienteMouseClicked

    private void tblPesquisaClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPesquisaClienteMousePressed
        // TODO add your handling code here:

        setarCampos();

    }//GEN-LAST:event_tblPesquisaClienteMousePressed

    private void btnOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrcamentoActionPerformed
        // TODO add your handling code here:

        //atribuindo um texto a variavel se selecionado o botão
        tipo = "Orçamento";
        System.out.println(tipo);


    }//GEN-LAST:event_btnOrcamentoActionPerformed

    private void btnOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSActionPerformed
        // TODO add your handling code here:

        tipo = "Ordem de Serviço";


    }//GEN-LAST:event_btnOSActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:

        //Ao abrir o form Orçamento
        btnOrcamento.setSelected(true);
        tipo = "Orçamento";

    }//GEN-LAST:event_formInternalFrameOpened

    private void txtPesquisaClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaClienteKeyReleased
        // TODO add your handling code here:

        pesquisarClientes();
    }//GEN-LAST:event_txtPesquisaClienteKeyReleased

    private void btnPesquisarOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarOSActionPerformed
        // TODO add your handling code here:

        pesquisarOS();
    }//GEN-LAST:event_btnPesquisarOSActionPerformed

    private void btnEditarOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarOSActionPerformed
        // TODO add your handling code here:

        alterarOS();
    }//GEN-LAST:event_btnEditarOSActionPerformed

    private void btnDeletarOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarOSActionPerformed
        // TODO add your handling code here:

        excluirOS();
    }//GEN-LAST:event_btnDeletarOSActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        
       imprimirOS();
    }//GEN-LAST:event_btnImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarOS;
    private javax.swing.JButton btnDeletarOS;
    private javax.swing.JButton btnEditarOS;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JRadioButton btnOS;
    private javax.swing.JRadioButton btnOrcamento;
    private javax.swing.JButton btnPesquisarOS;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboSituacao;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblPesquisaCliente;
    private javax.swing.JTextField txtDataOS;
    private javax.swing.JTextField txtDefeito;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtIdOs;
    private javax.swing.JTextField txtPesquisaCliente;
    private javax.swing.JTextField txtPesquisaIDCliente;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JTextField txtServico;
    private javax.swing.JTextField txtTecnico;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
