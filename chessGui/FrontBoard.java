/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package chessGui;

import ChessCore.BoardFile;
import ChessCore.BoardRank;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import ChessCore.ClassicChessGame;
import ChessCore.Move;
import ChessCore.Pieces.*;
import static ChessCore.Player.*;
import ChessCore.Square;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Yomna
 */
public class FrontBoard extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form Board
     */
    public final JButton[][] board;
    private JButton firstClick;
    private JButton secondClick;
    int row,col;
    public ClassicChessGame game=new ClassicChessGame();

    public FrontBoard() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(8, 8));
        setSize(900,600);
        
        
        board = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square Tile = new Square(BoardFile.values()[i], BoardRank.values()[j]);//create backend square
                Piece p = game.getPieceAtSquare(Tile);//get Piece at that location
                row=i;
                col=j;

                JButton square = new JButton();
                board[i][j] = square;
                square.putClientProperty("row", row); // Store the row index
                square.putClientProperty("col", col); // Store the column index
                add(square);
                board[i][j].addActionListener(this);
                square.setSize(30, 30);
                if ((i + j) % 2 == 0) {
                    square.setBackground(Color.WHITE);
                } else {
                    square.setBackground(Color.BLACK);
                }
//adding icons to buttons
                 setImg(p,square);
                 

            }

        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public JButton[][] getBoard() {
        return board;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void actionPerformed(ActionEvent e) {
       
                        JButton currentButton = (JButton) e.getSource();

   int currentRow = (int) currentButton.getClientProperty("row");
    int currentCol = (int) currentButton.getClientProperty("col");

    if (firstClick == null) {
        // First click
        firstClick = currentButton;
//        firstClick.setForeground(Color.yellow);
    } else {
        // Second click
        
        
//        JOptionPane.showMessageDialog(rootPane, ""+game.getGameStatus());
        secondClick = currentButton;
        int firstRow = (int) firstClick.getClientProperty("row");
        int firstCol = (int) firstClick.getClientProperty("col");
        
        // Perform your logic with the row and column information
        //System.out.println("First Click: Row " + firstRow + ", Column " + firstCol);
        //System.out.println("Second Click: Row " + currentRow + ", Column " + currentCol);
         Square s1 = new Square(BoardFile.values()[firstRow], BoardRank.values()[firstCol]);
        
         Square s2 =new Square(BoardFile.values()[currentRow], BoardRank.values()[currentCol]);
         Move m=new Move(s1,s2);
         if (game.makeMove(m)){
        //switch icons
        setImg(game.getPieceAtSquare(s1),firstClick);
        setImg(game.getPieceAtSquare(s2),secondClick);
        this.setTitle(""+game.getGameStatus());
        if(game.isGameEnded())
        {
            JOptionPane.showMessageDialog(rootPane, ""+game.isGameEnded());
        }
//         ImageIcon ic=(ImageIcon) firstClick.getIcon();
//         secondClick.setIcon(ic);
//         firstClick.setIcon(null);
         
        
                 }
         else{
             JOptionPane.showMessageDialog(rootPane, "Invalid Move");
         }
       
        // Reset flag
        firstClick = null;
    }
  
    }
    public void setImg(Piece p,JButton square){
        if(p==null){
            square.setIcon(null);}
          if (p != null && p.getOwner() == WHITE) {

                    if (p instanceof Pawn) {
                        Image image = new ImageIcon(getClass().getResource("/PieceImages/WhitePawn.png")).getImage();
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(80, 80, 80));
                        square.setIcon(icon);

                    }
                    else if(p instanceof Knight)
                    {
                        Image image = new ImageIcon(getClass().getResource("/PieceImages/WhiteKnight.png")).getImage();
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(80,80,80));
                        square.setIcon(icon);
                    }
                      else if(p instanceof Queen)
                    {
                        Image image = new ImageIcon(getClass().getResource("/PieceImages/WhiteQueen.png")).getImage();
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(80,80,80));
                        square.setIcon(icon);
                    }
                        else if(p instanceof King)
                    {
                        Image image = new ImageIcon(getClass().getResource("/PieceImages/WhiteKing.png")).getImage();
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(80,80,80));
                        square.setIcon(icon);
                    }
                          else if(p instanceof Rook)
                    {
                        Image image = new ImageIcon(getClass().getResource("/PieceImages/WhiteRook.png")).getImage();
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(80,80,80));
                         square.setIcon(icon);
                    }
                            else if(p instanceof Bishop)
                    {
                        Image image = new ImageIcon(getClass().getResource("/PieceImages/WhiteBishop.png")).getImage();
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(80,80,80));
                        square.setIcon(icon);
                    }


                }
                if (p != null && p.getOwner() == BLACK) {

                    if (p instanceof Pawn) {
                        Image image = new ImageIcon(getClass().getResource("/PieceImages/BlackPawn.png")).getImage();
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(80,80,80));
                        square.setIcon(icon);

                    }
                    else if(p instanceof Knight)
                    {
                        Image image = new ImageIcon(getClass().getResource("/PieceImages/BlackKnight.png")).getImage();
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(80,80,80));
                        square.setIcon(icon);
                    }
                      else if(p instanceof Queen)
                    {
                        Image image = new ImageIcon(getClass().getResource("/PieceImages/BlackQueen.png")).getImage();
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(80,80,80));
                        square.setIcon(icon);
                    }
                        else if(p instanceof King)
                    {
                        Image image = new ImageIcon(getClass().getResource("/PieceImages/BlackKing.png")).getImage();
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(80,80,80));
                        square.setIcon(icon);
                    }
                          else if(p instanceof Rook)
                    {
                        Image image = new ImageIcon(getClass().getResource("/PieceImages/BlackRook.png")).getImage();
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(80,80,80));
                       square.setIcon(icon);
                    }
                            else if(p instanceof Bishop)
                    {
                        Image image = new ImageIcon(getClass().getResource("/PieceImages/BlackBishop.png")).getImage();
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(80,80,80));
                        square.setIcon(icon);
                    }


                }
    }

    /**
     * @param args the command line arguments //
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrontBoard().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
