import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TrashTicTacToe {
        // board
        int board_height = 750;
        int board_width = 700;

        JFrame gyatFrame = new JFrame();
        JLabel gyatLabel = new JLabel(); // For text "tic trash toe"
        JPanel gyatPanel = new JPanel();  // panel for "tic trash toe"
        JPanel boardPanel = new JPanel();
        JButton[][] board = new JButton[3][3]; // a 3x3 board

        // Players
        String moggerX = "X";
        String moggerO = "O";
        String currenMogger = moggerX;

        boolean gameOver = false; // so not end
        int Turny = 0;

        TrashTicTacToe(){
            gyatFrame.setVisible(true);
            gyatFrame.setTitle("Trash tic tac toe");
            gyatFrame.setSize(board_width, board_height);
            gyatFrame.setResizable(true);
            gyatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gyatFrame.setLayout(new BorderLayout());;

            gyatLabel.setBackground(new Color(99, 69, 51));
            gyatLabel.setForeground(new Color(220, 180, 148));
            gyatLabel.setFont(new Font("Arial", Font.BOLD, 60));
            gyatLabel.setHorizontalAlignment(JLabel.CENTER);
            gyatLabel.setVerticalAlignment(JLabel.CENTER);
            gyatLabel.setText(">> Tic-Trash-Toe <<");
            gyatLabel.setOpaque(true);

            gyatPanel.setLayout(new BorderLayout()); // set Layout to border like display: inline
            gyatPanel.add(gyatLabel); // load la bel to panel
            gyatFrame.add(gyatPanel, BorderLayout.NORTH)  ; // load panel into frame

            boardPanel.setLayout(new GridLayout(3, 3));
            boardPanel.setBackground(new Color(99, 69, 51));
            gyatFrame.add(boardPanel);

            for(int row = 0; row < 3; row++){
                for(int column = 0; column < 3; column++){
                    JButton aButton =new JButton(); // creating a button
                    board[row][column] = aButton; // Keep track of all the buttons => for later player click AND check player
                    boardPanel.add(aButton); // add all this button to the board 3x3
                    aButton.setBackground(new Color(99, 69, 51)); // set chocolate background
                    aButton.setForeground(new Color(205, 193, 133)); // set dark brown text
                    aButton.setFont(new Font("MV Boli", Font.BOLD, 200));
                    aButton.setFocusable(false); // will remove boxes surrouding text
                    // aButton.setText(currenMogger);

                    // addEventListener to aButton in board 3x3
                    aButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            if(gameOver){ // If game's over then WE CANNOT Play
                                return;
                            }
                            JButton aButton = (JButton) e.getSource(); // when click on Button
                            
                            // check: if button has a text already, so player cannot REclick.
                            if (aButton.getText() == ""){
                                    Turny = Turny + 1; // check turn takes place before checkWinner
                                    aButton.setText(currenMogger);  // set to X or O
                                    checkWinner(); // Check for winner if they get three in a line
                                    if (!gameOver){ // (!gameOver = false)
                                        currenMogger = currenMogger == moggerX ? moggerO : moggerX;
                                        // Explain:
                                        // if (currenMogger == moggerX) {
                                        //     currenMogger = moggerO;
                                        // } else {
                                        //     currenMogger = moggerX;
                                        // }
                                        gyatLabel.setText("- "+ currenMogger +"'s turn -");
                                    }
                                }
                        }
                    });
                }
            }
        }

        void checkWinner(){
            // three horizontally |x|x|x|
            for (int row = 0; row < 3; row++){
                // check x|x|x AND row 1, 2, 3: _|x|x -> havent click
                if(board[row][0].getText() == board[row][1].getText() && board[row][0].getText() == board[row][2].getText()
                    && board[row][0].getText() != ""){
                    for(int col = 0; col < 3; col++){
                        setWinner(board[row][col]);
                    }
                    gameOver = true; // true = end game
                    return;
                }
            // three Vertically
            for (int col = 0; col < 3; col++){
                // check x/x/x verticallly AND check 0/X/X vertically      
                if(board[0][col].getText() == board[1][col].getText() && board[0][col].getText() == board[2][col].getText()
                    && board[0][col].getText() != ""){
                    for (row = 0; row < 3; row++ )
                    {
                        setWinner(board[row][col]);
                    }
                    gameOver = true;
                    return;
                }
            }
            // three cross // diagonally // only 2 scenerios
            if(board[0][0].getText() == board[1][1].getText() && board[0][0].getText() == board[2][2].getText()
                && board[0][0].getText() != "")
            {
                for (int i = 0; i < 3; i++)
                {
                    setWinner(board[i][i]);
                }
                gameOver = true;
                return;
            }
            if(board[0][2].getText() == board[1][1].getText() && board[0][2].getText() == board[2][0].getText()
                && board[0][2].getText() != "")
            {
                setWinner(board[0][2]);
                setWinner(board[1][1]);
                setWinner(board[2][0]);
                gameOver = true;
                return;
            }
            
            //Tie
            if (Turny == 9)
            {
                gyatLabel.setText("Tie!");
                for(int r = 0; r < 3; r++)
                {
                    for(int c = 0; c < 3; c++)
                    {
                        setTie(board[r][c]);
                    }
                }
                gameOver = true;
            }
        }
    }

    void setWinner(JButton aButton){
        aButton.setBackground( new Color(253, 190, 135));
        aButton.setForeground(Color.red);
        gyatLabel.setText("- "+ currenMogger + " Win! -");
    }

    void setTie(JButton aButton){
            aButton.setBackground(new Color(220, 180, 148));
            aButton.setForeground(new Color(255, 140, 0));
    }
}
