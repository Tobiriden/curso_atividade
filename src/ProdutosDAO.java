/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO produtos (Nome, valor, status) VALUES(?,?,?)");
            st.setString(1,produto.getNome());          
            st.setDouble(2,produto.getValor());
            st.setString(3, produto.getStatus());
            status = st.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso");
            
        } catch (SQLException ex) {          
            JOptionPane.showMessageDialog(null,"falha ao cadastrar");
            
        }
    }
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        String sql = "SELECT * FROM produtos";
        
        try {           
            conn = new conectaDAO().connectDB();
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();                    
            
            while (rs.next()) { 
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setNome(rs.getString("Nome"));
                produto.setId(rs.getInt("id"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                
                listagem.add(produto);    
            }           
            return listagem;
            
        } catch (Exception e) {           
            return null;
            
        }         
    }
public void venderProduto(int id)
{
    int status;
        try {
            conn = new conectaDAO().connectDB();
            st = conn.prepareStatement("UPDATE produtods SET status = ? where id = ?");
            st.setString(1,"Vendido");
            st.setInt(2,id);
            status = st.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Produto vendido com sucesso"); 
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            JOptionPane.showMessageDialog(null,"Falha ao realizar a venda");
        }
}
public ArrayList<ProdutosDTO> listarProdutosVendidos()
{
    
}
    
    
        
}

