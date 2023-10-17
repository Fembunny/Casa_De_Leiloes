package dao;

import beans.ProdutosDTO;
import conexao.conectaDAO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    private conectaDAO conexao;
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    
    public ProdutosDAO() {
        this.conexao = new conectaDAO();
        this.conn = this.conexao.connectDB();
    }
    
    public int cadastrarProduto(ProdutosDTO produto){
        int status;
        try{
            st = conn.prepareStatement("INSERT INTO Produtos (nome, valor, status) VALUES (?,?,?)");
            st.setString(1, produto.getNome());
            st.setDouble(2,produto.getValor());
            st.setString(3, produto.getStatus());
            
            status = st.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto salvo com sucesso ✔", "Cadastro de produtos", JOptionPane.PLAIN_MESSAGE);
            return status;
        }
        catch(SQLException ex){
            System.out.println("Erro ao conectar - " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao salvar produto, tente novamente", "Cadastro de produtos", JOptionPane.PLAIN_MESSAGE);
            return ex.getErrorCode();
        }
    }
    
    public int venderProduto(int id){
        int status;
        try{
            st = conn.prepareStatement("UPDATE Produtos SET status = ? WHERE id = ?");
            st.setString(1, "Vendido");
            st.setInt(2, id);
            
            status = st.executeUpdate();
            
            System.out.println("Produto vendido!");
            return status; 
        } catch(SQLException ex){
            System.out.println("Erro ao atualizar - " + ex.getErrorCode());
            return ex.getErrorCode();
        }
    }
    
    public List<ProdutosDTO> listarProdutos() { 
        String sql = "SELECT * FROM Produtos";
        
        try {
            st = this.conn.prepareStatement(sql);

            rs = st.executeQuery();    
            
            List<ProdutosDTO> lista = new ArrayList<>();
            
            while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
          
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getDouble("valor"));
            produto.setStatus(rs.getString("status"));

            lista.add(produto);                   
            }                  
            
            return lista;   
        } catch (SQLException e) {
            return null;
        }
    }
    
    public List<ProdutosDTO> listarProdutosVendidos(String status) { 
        String sql = "SELECT * FROM Produtos WHERE status LIKE ?";
        
        try {
            st = this.conn.prepareStatement(sql);
            
            st.setString(1,"%" + status + "%");
            rs = st.executeQuery();    
            
            List<ProdutosDTO> lista = new ArrayList<>();
            
            while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
          
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getDouble("valor"));
            produto.setStatus(rs.getString("status"));

            lista.add(produto);                   
            }                  
            
            return lista;   
        } catch (SQLException e) {
            return null;
        }
    }
    
    
    
    
    
        
}

