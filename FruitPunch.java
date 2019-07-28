//Name: Niveditha
//Date: May 28th,2016
//Game: Fruit Punch (Candy Crush)
/* Picture Sources:
 Fruit Icons taken from:
 https://dribbble.com/shots/1340321-Fruit-Icons
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.Dimension;

public class FruitPunch extends Applet implements ActionListener
{
    Panel p_screen; //to hold all of the screens
    Panel titlescreen, homescreen, instructionscreen, levelscreen, highscores, levelselect, settingspage; //the screens
    CardLayout cdLayout = new CardLayout ();
    JButton returnn, returnnn, next, shuffle, arial, cambria, forte;
    JLabel valid, printB, scorelabel, leveltitle;
    String fontname = "Forte"; // The font can be changed if it's too hard to read
    int moves = 0;
    int row = 6; // The size can be changed later, but this is the default
    int col = 6;
    int last = -1;
    int score = 0;
    int level = 0;
    int b[] = new int [row * col]; // The int array behind the LevelScreen grid
    JButton a[] = new JButton [row * col]; // The button array in the LevelScreen grid
    JButton c[] = new JButton [5]; // Displays level selection on LevelSelect screen
    JButton rowarray[] = new JButton [4];
    JButton colarray[] = new JButton [4];
    int levelmoves[] = {10, 13, 15, 16, 20}; // Move limit of the levels
    int targetscores[] = {200, 300, 400, 450, 600}; // Target scores of the levels
    int myscores[] = new int [5]; // Stores user's high scores
    JTextArea TA2, TA; // Displays score/moves left/matches left

	public static void main(String args[]) {
		new FruitPunch();
	}

    public FruitPunch ()
    {
	resize (1000, 600);
	p_screen = new Panel ();
	p_screen.setLayout (cdLayout);
	titlescreen ();
	homescreen ();
	instructionscreen ();
	levelscreen ();
	levelselect ();
	highscores ();
	settingspage ();
	setLayout (new BorderLayout ());
	add ("Center", p_screen);
    }


    public void titlescreen ()
    { // opening image
	titlescreen = new Panel ();
	titlescreen.setBackground (new Color (201, 229, 224));
	JButton pic = new JButton (createImageIcon ("FPtitlescreen.jpg"));
	pic.setActionCommand ("homescreen");
	pic.addActionListener (this);
	titlescreen.add (pic);
	p_screen.add ("titlescreen", titlescreen);
    }


    public void formatButtons (JButton a, String AC)
    {
	a.setFont (new Font (fontname, Font.PLAIN, 24));
	a.setBackground (new Color (67, 175, 168));
	a.setForeground (new Color (232, 239, 240));
	a.setPreferredSize (new Dimension (150, 50));
	a.addActionListener (this);
	a.setActionCommand ("" + AC);
    }


    public void formatTitles (JLabel title, int size)
    {
	title.setForeground (new Color (223, 168, 98));
	title.setFont (new Font (fontname, Font.BOLD, size));
    }


    public void homescreen ()
    {
	homescreen = new Panel ();
	homescreen.setBackground (new Color (201, 229, 224));

	JPanel panel = new JPanel ();
	BoxLayout boxlayout = new BoxLayout (panel, BoxLayout.Y_AXIS);
	panel.setLayout (boxlayout);
	panel.setBorder (new EmptyBorder (new Insets (30, 100, 100, 100)));
	panel.setBackground (new Color (201, 229, 224));

	JLabel title = new JLabel ("Home");
	formatTitles (title, 72);

	JButton play = new JButton ("Play");
	formatButtons (play, "levelselect");

	JButton hs = new JButton ("High Scores");
	formatButtons (hs, "highscores");

	JButton rules = new JButton ("Rules");
	formatButtons (rules, "instructionscreen");

	JButton settings = new JButton ("Settings");
	formatButtons (settings, "settingspage");

	JButton extra = new JButton ("Return");
	formatButtons (extra, "titlescreen");

	Panel picpanel = new Panel ();
	JLabel one = new JLabel (createImageIcon ("0.jpg"));
	JLabel two = new JLabel (createImageIcon ("1.jpg"));
	picpanel.add (one);
	picpanel.add (two);

	panel.add (title);
	title.setAlignmentX (Component.CENTER_ALIGNMENT);
	panel.add (Box.createRigidArea (new Dimension (0, 20)));
	panel.add (play);
	play.setAlignmentX (Component.CENTER_ALIGNMENT);
	panel.add (Box.createRigidArea (new Dimension (0, 20)));
	panel.add (rules);
	rules.setAlignmentX (Component.CENTER_ALIGNMENT);
	panel.add (Box.createRigidArea (new Dimension (0, 20)));
	panel.add (hs);
	hs.setAlignmentX (Component.CENTER_ALIGNMENT);
	panel.add (Box.createRigidArea (new Dimension (0, 20)));
	panel.add (settings);
	settings.setAlignmentX (Component.CENTER_ALIGNMENT);
	panel.add (Box.createRigidArea (new Dimension (0, 20)));
	panel.add (extra);
	extra.setAlignmentX (Component.CENTER_ALIGNMENT);
	panel.add (Box.createRigidArea (new Dimension (0, 20)));
	panel.add (picpanel);

	homescreen.add (panel);

	p_screen.add ("homescreen", homescreen);
    }


    public void instructionscreen ()
    {
	instructionscreen = new Panel ();
	instructionscreen.setBackground (new Color (201, 229, 224));

	JPanel panel = new JPanel ();
	BoxLayout boxlayout = new BoxLayout (panel, BoxLayout.Y_AXIS); // To make all the widgets line up
	panel.setLayout (boxlayout);
	panel.setBorder (new EmptyBorder (new Insets (30, 80, 50, 80)));
	panel.setBackground (new Color (236, 234, 236));

	JLabel title = new JLabel ("Instructions");
	formatTitles (title, 72);

	JButton returnn = new JButton ("Return");
	formatButtons (returnn, "homescreen");

	TA = new JTextArea ("    Help the world eat fruits again! To send fruits off \n    to the grocery store, they must be paired in threes."
		+ " Every time you\n    pair three of the same kind of fruit, they disappear from the\n     table and more fruits come down."
		+ "You can click \n     two horizontally or vertically adjacent fruits to switch them."
		+ "\n\n    If the fruits aren't adjacent, an error message will apppear!"
		+ "  \n    Each pair of three is worth 20 points. Get the required number of matches \n    to proceed."
		+ "\n\n    Happy farming!", 3, 3);
	TA.setEnabled (false);
	TA.setFont (new Font (fontname, Font.PLAIN, 20));
	TA.setBackground (new Color (236, 234, 236));

	Panel scoreimages = new Panel ();
	JLabel img1 = new JLabel (createImageIcon ("4.jpg"));
	JLabel img2 = new JLabel (createImageIcon ("5.jpg"));
	//JLabel targetscore = new JLabel (createImageIcon ("target.jpg"));
	//JLabel targetscore2 = new JLabel (createImageIcon ("invalid.jpg"));
	scoreimages.add (img1);
	scoreimages.add (img2);
	//scoreimages.add (targetscore);
	//scoreimages.add (targetscore2);

	panel.add (title);
	title.setAlignmentX (Component.CENTER_ALIGNMENT);
	panel.add (TA);
	panel.add (scoreimages);
	panel.add (returnn);
	returnn.setAlignmentX (Component.CENTER_ALIGNMENT);

	instructionscreen.add (panel);
	p_screen.add ("instructionscreen", instructionscreen);
    }


    public void highscores ()
    {
	highscores = new Panel ();
	highscores.setBackground (new Color (236, 234, 236));

	JPanel panel = new JPanel ();
	BoxLayout boxlayout = new BoxLayout (panel, BoxLayout.Y_AXIS); // To make all the widgets line up
	panel.setLayout (boxlayout);
	panel.setBorder (new EmptyBorder (new Insets (30, 80, 50, 80)));
	panel.setBackground (new Color (201, 229, 224));

	JLabel title = new JLabel ("High Scores");
	formatTitles (title, 72);

	JLabel title2 = new JLabel ("Scores are calculated by subtracting the target score by your score,");
	formatTitles (title2, 14);

	JLabel title3 = new JLabel ("then adding it to the product of the moves left and 20. ");
	formatTitles (title3, 14);

	JButton returnn = new JButton ("Return");
	formatButtons (returnn, "homescreen");

	Panel scoreimages = new Panel ();
	JLabel img1 = new JLabel (createImageIcon ("2.jpg"));
	JLabel img2 = new JLabel (createImageIcon ("3.jpg"));
	scoreimages.add (img1);
	scoreimages.add (img2);

	JTextArea TA = new JTextArea ("    1) " + myscores [0] + "\n    2) " + myscores [1] + "\n    3) " + myscores [2]
		+ "\n    4) " + myscores [3] + "\n    5) " + myscores [4], 2, 2);
	TA.setEnabled (false);
	TA.setFont (new Font (fontname, Font.PLAIN, 42));
	TA.setBackground (new Color (201, 229, 224));
	TA.setForeground (new Color (223, 168, 98));

	panel.add (title);
	panel.add (title2);
	panel.add (title3);
	title.setAlignmentX (Component.CENTER_ALIGNMENT);
	title2.setAlignmentX (Component.LEFT_ALIGNMENT);
	title3.setAlignmentX (Component.LEFT_ALIGNMENT);
	panel.add (TA);
	panel.add (scoreimages);
	panel.add (returnn);
	returnn.setAlignmentX (Component.CENTER_ALIGNMENT);

	highscores.add (panel);
	p_screen.add ("highscores", highscores);
    }


    public void settingspage ()
    {
	settingspage = new Panel ();
	settingspage.setBackground (new Color (236, 234, 236));

	JPanel panel = new JPanel (); // this panel will hold everything in a boxlayout
	BoxLayout boxlayout = new BoxLayout (panel, BoxLayout.Y_AXIS);
	panel.setLayout (boxlayout);
	panel.setBorder (new EmptyBorder (new Insets (30, 100, 200, 200)));
	panel.setBackground (new Color (201, 229, 224));

	JLabel title = new JLabel ("Settings");
	formatTitles (title, 72);
	panel.add (title);

	JLabel title2 = new JLabel ("Select board size");
	formatTitles (title2, 24);
	title2.setAlignmentX (Component.LEFT_ALIGNMENT);
	panel.add (title2);

	Panel rowpanel = new Panel ();
	JLabel row1 = new JLabel ("How many rows?");
	formatTitles (row1, 20);

	Panel rowarraypanel = new Panel ();
	//    rowarray[] = new JButton [4];
	for (int i = 6 ; i < 10 ; i++)
	{ // AC = 300 to distinguish it from c[]
	    rowarray [i - 6] = new JButton ("" + i);
	    rowarray [i - 6].addActionListener (this);
	    String AC = ("" + (i + 20));
	    formatButtons (rowarray [i - 6], AC);
	    rowarraypanel.add (rowarray [i - 6]);
	}

	rowpanel.add (row1); // Looks like "How many rows? 6 7 8 9"
	rowpanel.add (rowarraypanel);
	panel.add (rowpanel);

	Panel colpanel = new Panel ();
	JLabel col1 = new JLabel ("How many columns?");
	formatTitles (col1, 20);

	Panel colarraypanel = new Panel ();
	// colarray[] = new JButton [4];
	for (int i = 6 ; i < 10 ; i++)
	{
	    colarray [i - 6] = new JButton ("" + i);
	    colarray [i - 6].addActionListener (this);
	    String AC = ("" + (i + 20));
	    formatButtons (colarray [i - 6], AC);
	    colarraypanel.add (colarray [i - 6]);
	}

	colpanel.add (col1);
	colpanel.add (colarraypanel);
	panel.add (colpanel);

	Panel fontpanel = new Panel ();
	JLabel choosefont = new JLabel ("Choose a font:");
	formatTitles (choosefont, 24);

	arial = new JButton ("Arial");
	arial.setActionCommand ("Arial");
	arial.addActionListener (this);
	arial.setFont (new Font ("Arial", Font.PLAIN, 24));
	arial.setBackground (new Color (67, 175, 168));
	arial.setForeground (new Color (232, 239, 240));
	arial.setPreferredSize (new Dimension (200, 50));

	forte = new JButton ("Forte");
	forte.setActionCommand ("Forte");
	forte.addActionListener (this);
	forte.setFont (new Font ("Forte", Font.PLAIN, 24));
	forte.setBackground (new Color (67, 175, 168));
	forte.setForeground (new Color (232, 239, 240));
	forte.setPreferredSize (new Dimension (200, 50));

	cambria = new JButton ("Cambria");
	cambria.setActionCommand ("Cambria");
	cambria.addActionListener (this);
	cambria.setFont (new Font ("Cambria", Font.PLAIN, 24));
	cambria.setBackground (new Color (67, 175, 168));
	cambria.setForeground (new Color (232, 239, 240));
	cambria.setPreferredSize (new Dimension (200, 50));
	panel.add (choosefont);
	fontpanel.add (arial);
	fontpanel.add (forte);
	fontpanel.add (cambria);
	panel.add (fontpanel);
	settingspage.add (panel);
	p_screen.add ("settingspage", settingspage);
    }


    public void levelselect ()
    {
	levelselect = new Panel ();
	levelselect.setBackground (new Color (236, 234, 236));

	JPanel panel = new JPanel ();
	BoxLayout boxlayout = new BoxLayout (panel, BoxLayout.Y_AXIS);
	panel.setLayout (boxlayout);
	panel.setBorder (new EmptyBorder (new Insets (30, 100, 500, 200)));
	panel.setBackground (new Color (201, 229, 224));

	Panel buttongrid = new Panel ();
	for (int i = 0 ; i < c.length ; i++)
	{
	    c [i] = new JButton ("" + (i + 1));
	    c [i].setEnabled (false);
	    c [i].setFont (new Font (fontname, Font.BOLD, 72));
	    c [i].setForeground (new Color (223, 168, 98));
	    c [i].setBackground (new Color (67, 175, 168));
	    c [i].setPreferredSize (new Dimension (150, 150));
	    c [i].addActionListener (this);
	    c [i].setActionCommand ("" + (i + 100));
	    buttongrid.add (c [i]);
	}
	c [0].setEnabled (true);


	JLabel title = new JLabel ("Level Select");
	formatTitles (title, 72);

	JLabel title2 = new JLabel ("Choose which level you would like to play.");
	formatTitles (title2, 24);

	Panel scoreimages = new Panel ();
	JLabel img1 = new JLabel (createImageIcon ("4.jpg"));
	JLabel img2 = new JLabel (createImageIcon ("0.jpg"));
	scoreimages.add (img1);
	scoreimages.add (img2);

	JButton returnn = new JButton ("Return");
	formatButtons (returnn, "homescreen");

	panel.add (title);
	title.setAlignmentX (Component.CENTER_ALIGNMENT);
	panel.add (Box.createRigidArea (new Dimension (0, 20)));
	panel.add (title2);
	title2.setAlignmentX (Component.CENTER_ALIGNMENT);
	panel.add (Box.createRigidArea (new Dimension (0, 20)));
	panel.add (buttongrid);
	panel.add (Box.createRigidArea (new Dimension (0, 20)));
	panel.add (scoreimages);
	panel.add (Box.createRigidArea (new Dimension (0, 20)));
	panel.add (returnn);
	returnn.setAlignmentX (Component.CENTER_ALIGNMENT);
	levelselect.add (panel);

	p_screen.add ("levelselect", levelselect);
    }


    public void levelscreen ()
    {
	leveltitle = new JLabel ("   Level " + (level + 1));
	formatTitles (leveltitle, 72);

	valid = new JLabel ("         Click two adjacent pieces to swap them.");
	formatTitles (valid, 24);

	Panel titles = new Panel (new BorderLayout ());
	titles.add (leveltitle, BorderLayout.NORTH);
	titles.add (valid, BorderLayout.SOUTH);

	moves = levelmoves [level];
	score = 0;

	TA2 = new JTextArea (" \nMatches left:\n "
		+ ((targetscores [level] - score) / 20)
		+ "\n\nScore:\n " + score + "\n\n Moves left:   \n" + moves);
	TA2.setEnabled (false);
	TA2.setForeground (new Color (223, 168, 98));
	TA2.setBackground (new Color (201, 229, 224));
	TA2.setFont (new Font (fontname, Font.BOLD, 24));

	// Make random grid array in b[]
	for (int m = 0 ; m < b.length ; m++)
	    b [m] = (int) (Math.random () * 6);

	Panel buttongrid = new Panel (new GridLayout (row, row));
	for (int i = 0 ; i < b.length ; i++)
	{
	    a [i] = new JButton (createImageIcon (b [i] + ".jpg"));
	    a [i].setPreferredSize (new Dimension (60, 60));
	    a [i].setOpaque (false);
	    a [i].setContentAreaFilled (false);
	    a [i].setBorderPainted (false);
	    a [i].addActionListener (this);
	    a [i].setActionCommand ("" + i);
	    buttongrid.add (a [i]);
	}
	checkThree (b);
	checkWin (score);

	JLabel space = new JLabel (" ");
	JLabel space2 = new JLabel (" ");
	JLabel space3 = new JLabel (" ");
	JLabel space4 = new JLabel ("                       ");
	space.setFont (new Font ("Forte", Font.BOLD, 32));
	space2.setFont (new Font ("Forte", Font.BOLD, 32));
	space3.setFont (new Font ("Forte", Font.BOLD, 32));
	space4.setFont (new Font ("Forte", Font.BOLD, 32));

	Panel spacingout = new Panel (new BorderLayout ());
	spacingout.add (buttongrid, BorderLayout.CENTER);
	spacingout.add (space, BorderLayout.SOUTH);
	spacingout.add (space2, BorderLayout.NORTH);
	spacingout.add (space3, BorderLayout.EAST);
	spacingout.add (space4, BorderLayout.WEST);

	next = new JButton ("Next Level");
	formatButtons (next, "next");

	JButton returnnn = new JButton ("Return");
	formatButtons (returnnn, "levelselect");

	JButton replay = new JButton ("Replay");
	formatButtons (replay, "replay");

	JButton cancel = new JButton ("Cancel");
	formatButtons (cancel, "cancel");

	shuffle = new JButton ("Shuffle");
	formatButtons (shuffle, "shuffle");

	Panel buttonpanel = new Panel ();
	buttonpanel.add (shuffle);
	buttonpanel.add (cancel);
	buttonpanel.add (next);
	buttonpanel.add (replay);
	buttonpanel.add (returnnn);

	Panel fullgame = new Panel (new BorderLayout ());
	fullgame.add (spacingout, BorderLayout.CENTER);
	fullgame.add (titles, BorderLayout.NORTH);
	fullgame.add (buttonpanel, BorderLayout.SOUTH);
	fullgame.add (TA2, BorderLayout.EAST);

	Panel levelscreen = new Panel (new BorderLayout ());
	levelscreen.setBackground (new Color (201, 229, 224));
	levelscreen.add (fullgame, BorderLayout.CENTER);
	p_screen.add ("levelscreen", levelscreen);

    }


    public void checkThree (int b[])
    { // The method that checks for threes, eliminates them, and counts them toward the score
	boolean gotpoints1 = true;
	boolean gotpoints2 = true; // so that more than one removal can happen each method call
	while (gotpoints1 == true || gotpoints2 == true)
	{
	    // HORIZONTAL CHECK
	    for (int i = 2 ; i < b.length ; i++)
	    { // i is 2 because it starts the check at the third element, then compares it to the two previous elements
		if ((b [i] == b [i - 1] && b [i - 1] == b [i - 2]) && (i % row >= 2))
		{ // if three consecutive elements of b are equal, if i is not the first or second in the row
		    if (i >= (row + 2))
		    { // row two, spot 3 and onwards
			for (i = i ; i >= row ; i -= row)
			{ // replace b[i] with b[i-6]
			    b [i] = b [i - row];
			    b [i - 1] = b [(i - 1) - row];
			    b [i - 2] = b [(i - 2) - row];
			}
			b [i % row] = (int) (Math.random () * 5); // the corresponding buttons at the top of the column, three randoms
			b [(i - 1) % row] = (int) (Math.random () * 5);
			b [(i - 2) % row] = (int) (Math.random () * 5);
			if (b [(i - 1) % row] == b [i % row]) // if the corresponding top button ends up being the same as the top-1 button
			    b [(i - 1) % row] = (int) (Math.random () * 5); // give it a new value
			score += 20;
		    }
		    else
		    { // all three pieces must be randomized, nothing is swapped down
			b [i] = (int) (Math.random () * 5);
			b [i - 1] = (int) (Math.random () * 5);
			b [i - 2] = (int) (Math.random () * 5);
			if (b [i] == b [i - 1]) // if they're the same
			    b [(i - 1)] = (int) (Math.random () * 5); // give it a new value
			score += 20;

		    }
		    gotpoints1 = true;
		}
		else
		    gotpoints1 = false;
	    }

	    // VERTICAL CHECK
	    for (int i = (col * 2) ; i < b.length ; i++)
	    { // start on third row, then check two above it
		if (b [i] == b [i - col] && b [i - col] == b [i - (col * 2)])
		{
		    if (i >= (col * (col - 1)))
		    { // if i is in the bottom row, swap three downward
			b [i] = b [i - (col * 3)];
			b [i - col] = b [(i - col) - (col * 3)];
			b [i - (col * 2)] = b [(i - (col * 2)) - (col * 3)];
			b [i % col] = (int) (Math.random () * 5); // make three new pieces
			b [(i % col) + col] = (int) (Math.random () * 5);
			b [(i % col) + (col * 2)] = (int) (Math.random () * 5);
			if (b [i % col] == b [(i % col) + col]) // if they're the same
			    b [(i % col) + col] = (int) (Math.random () * 5); // give it a new value
		    }
		    else if (i >= (col * 4))
		    { // if i is in the fifth row, swap two downward
			b [i] = b [i - (col * 3)];
			b [i - col] = b [(i - col) - (col * 3)];
			b [i % (col * 2)] = (int) (Math.random () * 5); // make three new pieces
			b [(i % col) + col] = (int) (Math.random () * 5);
			b [(i % col) + (col * 2)] = (int) (Math.random () * 5);
			if (b [i % col] == b [(i % col) + col]) // if they're the same
			    b [(i % col) + col] = (int) (Math.random () * 5); // give it a new value
		    }
		    else if (i >= (col * 3))
		    {
			b [i] = b [i - (col * 3)];
			b [i % col] = (int) (Math.random () * 5);
			b [(i % col) + col] = (int) (Math.random () * 5);
			b [(i % col) + (col * 2)] = (int) (Math.random () * 5);
			if (b [i % col] == b [(i % col) + col]) // if they're the same
			    b [(i % col) + col] = (int) (Math.random () * 5); // give it a new value
		    }
		    else
		    {
			b [i] = (int) (Math.random () * 5);
			b [i - col] = (int) (Math.random () * 5);
			b [i - (col * 2)] = (int) (Math.random () * 5);
			if (b [i - col] == b [i]) // if they're the same
			    b [(i - col)] = (int) (Math.random () * 5); // give it a new value
		    }
		    score += 20;
		    gotpoints2 = true;
		}
		gotpoints2 = false;
	    }
	}

	if (moves >= 0)
	    TA2.setText (" \nMatches left:\n " + ((targetscores [level] - score) / 20) + "\n\nScore:\n "
		    + score + "\n\n Moves left:   \n" + moves);
	else
	    TA2.setText (" \nMatches left:\n " + ((targetscores [level] - score) / 20) + "\n\nScore:\n "
		    + score + "\n\n Moves left:   \n" + moves + "\n\n You lose!");
	if (score > myscores [level]) // if it's bigger, then switch it in the highscores
	{
	    myscores [level] = score;
	    TA = new JTextArea ("    1) " + myscores [0] + "\n    2) " + myscores [1] + "\n    3) " + myscores [2]
		    + "\n    4) " + myscores [3] + "\n    5) " + myscores [4]);
	}

	checkWin (score);
	redraw ();
    }


    public void redraw ()
    {
	for (int q = 0 ; q < b.length ; q++)
	{
	    a [q].setIcon (createImageIcon (b [q] + ".jpg"));
	}
    }


    public void randomredraw ()
    {
	for (int k = 0 ; k < b.length ; k++)
	    b [k] = (int) (Math.random () * 5);
	for (int i = 0 ; i < b.length ; i++)
	{
	    a [i].setIcon (new ImageIcon (b [i] + ".jpg"));
	    a [i].setPreferredSize (new Dimension (60, 60));
	    a [i].setOpaque (false);
	    a [i].setContentAreaFilled (false);
	    a [i].setBorderPainted (false);
	}
    }


    public void replay ()
    { // Allows user to replay the level
	randomredraw ();
	for (int i = 0 ; i < b.length ; i++)
	    a [i].setEnabled (true); // make all pieces clickable again
	next.setEnabled (false);
	moves = levelmoves [level];
	score = 0;
	TA2.setText (" \nMatches left:\n "
		+ ((targetscores [level] - score) / 20)
		+ "\n\nScore:\n " + score + "\n\n Moves left:   \n" + moves);
	cdLayout.show (p_screen, "levelscreen");
    }


    public void next ()
    { // Go to next level
	cdLayout.show (p_screen, "levelselect"); // always return to the level screen before progressing
	level++;
	leveltitle.setText ("   Level " + (level + 1)); // it doesn't update without this for some reason
	score = 0;
	TA2.setText (" \nMatches left:\n "
		+ ((targetscores [level] - score) / 20)
		+ "\n\nScore:\n " + score + "\n\n Moves left:   \n" + moves);
	next.setEnabled (false);
	shuffle.setEnabled (true); // shuffle was disabled the moment they reached the target
	for (int i = 0 ; i < b.length ; i++)
	    a [i].setEnabled (true); // make all pieces clickable again
    }


    public void dealWithN (int n)
    {
	if (n >= 300)
	{
	    rowarray [n - 300].setBackground (Color.red);
	    row = (n - 300);
	}
	else if (n >= 200)
	{
	    colarray [n - 300].setBackground (Color.red);
	    col = (n - 300);
	}
	else if (n >= 100)
	    NisaLevelSelection (n);
	else
	    swappingMethod (n);
    }


    public void NisaLevelSelection (int n)
    {
	level = (n - 100); // get the real level
	for (int i = 0 ; i < b.length ; i++)
	    a [i].setEnabled (true); // make all pieces clickable again
	moves = levelmoves [level];
	score = 0;
	shuffle.setEnabled (true); // because otherwise you can only shuffle if you click next
	cdLayout.show (p_screen, "levelscreen");
    }


    public void swappingMethod (int n)
    { // swaps the two pieces if it's a valid move
	{
	    if (last == -1)
	    {
		last = n;
		valid.setText ("     Click two adjacent pieces to swap them.");
	    }
	    else
	    {
		if (isValid (n, last) == true)
		{
		    int temp = b [last];
		    a [last].setIcon (new ImageIcon (b [n] + ".jpg"));
		    a [n].setIcon (new ImageIcon (temp + ".jpg"));
		    b [last] = b [n];
		    b [n] = temp;
		    last = -1;
		    checkThree (b);
		    moves--;
		}
		else
		{
		    valid.setText ("     That move is invalid.");
		    last = -1;
		}
	    }
	}
    }


    public void checkWin (int score)
    { // checks if victory, deals with aftermath
	if ((targetscores [level] - score) / 20 <= 0) // if there are no matches left
	{
	    TA2.setText (" \nMatches left:\n "
		    + ((targetscores [level] - score) / 20)
		    + "\n\nScore:\n " + score + "\n\n Moves left:   \n" + moves + "\n\n You did it!");
	    next.setEnabled (true);
	    c [level + 1].setEnabled (true); // enable the button for the next level
	    shuffle.setEnabled (false); // otherwise, you can continue to get points through shuffling
	    for (int i = 0 ; i < b.length ; i++)
		a [i].setEnabled (false); // once you pass the level, you can't click any pieces anymore
	}
	if (score > myscores [level]) // if it's bigger, then switch it in the highscores
	{
	    myscores [level] = score;
	    TA = new JTextArea ("    1) " + myscores [0] + "\n    2) " + myscores [1] + "\n    3) " + myscores [2]
		    + "\n    4) " + myscores [3] + "\n    5) " + myscores [4], 2, 2);
	}
    }


    public boolean isValid (int n, int last)
    { // Checks if the two pieces are adjacent
	if (n == (last + 1) || n == (last - 1) || n == (last + col) || n == (last - col))
	    return true;
	else
	    return false;
    }


    public void setFontSelected (String m)
    {
	if (m.equals ("Arial"))
	{
	    arial.setBackground (Color.red);
	    forte.setBackground (new Color (67, 175, 168));
	    cambria.setBackground (new Color (67, 175, 168));
	    }
	else if (m.equals ("Forte"))
	{
	    forte.setBackground (Color.red);
	    arial.setBackground (new Color (67, 175, 168));
	    cambria.setBackground (new Color (67, 175, 168));
	    }
	else
	{
	    cambria.setBackground (Color.red);
	    arial.setBackground (new Color (67, 175, 168));
	    forte.setBackground (new Color (67, 175, 168));}

    }


    public void actionPerformed (ActionEvent e)
    {
	String m = e.getActionCommand ();
	if (m.equals ("homescreen") || m.equals ("settingspage") || m.equals ("instructionscreen") || m.equals ("titlescreen") || m.equals ("levelselect") || m.equals ("highscores"))
	    cdLayout.show (p_screen, m);

	else if (m.equals ("Arial") || m.equals ("Forte") || m.equals ("Cambria"))
	{
	    fontname = m;
	    setFontSelected (m);
	}
	else if (m.equals ("cancel"))
	    last = -1; // if the user clicks the wrong button, they can un-select
	else if (m.equals ("replay"))
	    replay ();
	else if (m.equals ("next"))
	    next ();
	else if (m.equals ("shuffle"))
	{
	    randomredraw ();
	    moves = moves - 2; // shuffling costs two moves
	    TA2.setText (" \nMatches left:\n "
		    + ((targetscores [level] - score) / 20)
		    + "\n\nScore:\n " + score + "\n\n Moves left:   \n" + moves);
	}
	else
	{
	    int n = Integer.parseInt (e.getActionCommand ());
	    dealWithN (n);
	}
    }


    protected static ImageIcon createImageIcon (String path)
    {
	java.net.URL imgURL = FruitPunch.class.getResource (path);
	if (imgURL != null)
	{
	    return new ImageIcon (imgURL);
	}

	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }
}
