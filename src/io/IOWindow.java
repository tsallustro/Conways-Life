package io;

import java.awt.Dialog;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import std.CellMap;
import std.Exe;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

public class IOWindow extends JFrame implements ActionListener {

	private JFormattedTextField fieldX;
	private JFormattedTextField fieldY;
	private ButtonGroup radios;
	private JButton btnStart, btnAdd;
	private JRadioButton rdbnGlider, rdbnSingle, rdbnPulsar;
	private Shape selectedShape = Shape.NONE;
	private Thread parent;
	private static enum Shape {
		NONE, SINGLE, GLIDER, PULSAR;
	}

	/**
	 * Create the application.
	 */
	public IOWindow(Thread parent) {
		this.parent = parent;
		initialize();
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		this.setBounds(Exe.win.getX() + Exe.win.getWidth(), Exe.win.getY(), 450, 300);
		getContentPane().setLayout(null);

		JLabel lblX = new JLabel("X Position");
		lblX.setBounds(30, 35, 63, 16);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblX);

		fieldX = new JFormattedTextField(NumberFormat.getIntegerInstance());
		fieldX.setBounds(188, 30, 75, 26);
		fieldX.setText("0");
		this.getContentPane().add(fieldX);
		fieldX.setColumns(10);

		JLabel lblY = new JLabel("Y Position");
		lblY.setBounds(30, 96, 63, 16);
		lblY.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblY);

		fieldY = new JFormattedTextField(NumberFormat.getIntegerInstance());
		fieldY.setBounds(188, 91, 75, 26);
		fieldY.setText("0");
		this.getContentPane().add(fieldY);
		fieldY.setColumns(10);

		JLabel lblShape = new JLabel("Shape");
		lblShape.setBounds(43, 152, 37, 16);
		lblShape.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblShape);

		rdbnSingle = new JRadioButton("Single cell");
		rdbnSingle.setBounds(328, 173, 96, 23);
		this.getContentPane().add(rdbnSingle);

		rdbnGlider = new JRadioButton("Glider (3x3)");
		rdbnGlider.setBounds(328, 201, 105, 23);
		this.getContentPane().add(rdbnGlider);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(188, 229, 75, 29);
		this.getContentPane().add(btnAdd);

		rdbnPulsar = new JRadioButton("Pulsar (15x15)");
		rdbnPulsar.setBounds(328, 229, 122, 23);
		this.getContentPane().add(rdbnPulsar);

		btnStart = new JButton("start");
		btnStart.setEnabled(false);
		btnStart.addActionListener(this);
		btnStart.setBounds(30, 229, 117, 29);
		btnStart.setToolTipText("Not enabled until something is added");
		getContentPane().add(btnStart);

		// Add action listeners
		btnAdd.addActionListener(this);
		rdbnPulsar.addActionListener(this);
		rdbnSingle.addActionListener(this);
		rdbnGlider.addActionListener(this);
		// Set up radio button group
		radios = new ButtonGroup();
		radios.add(rdbnPulsar);
		radios.add(rdbnGlider);
		radios.add(rdbnSingle);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnAdd)) {
			if (this.selectedShape != Shape.NONE) {

				try {
					fieldX.commitEdit();
					fieldY.commitEdit();
					this.submit();
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(this, "Both fields must be whole numbers greater than 0", "Error",
							JOptionPane.WARNING_MESSAGE);
					fieldX.setText("0");
					fieldY.setText("0");
				}

			} else {
				JOptionPane.showMessageDialog(this, "You must select a shape", "Error", JOptionPane.WARNING_MESSAGE);
			}
		} else if (e.getSource().equals(btnStart)) {
			parent.interrupt();
			this.dispose();
		} else if (e.getSource().equals(rdbnSingle)) {
			this.selectedShape = Shape.SINGLE;
		} else if (e.getSource().equals(rdbnPulsar)) {
			this.selectedShape = Shape.PULSAR;
		} else if (e.getSource().equals(rdbnGlider)) {
			this.selectedShape = Shape.GLIDER;
		}

	}

	private void submit() {
		switch (this.selectedShape) {
			case SINGLE: {
				Number numx = (Number) this.fieldX.getValue(), numy = (Number) this.fieldX.getValue();

				if (!Exe.MAP.setCell(numx.intValue(), numy.intValue(), true)) {
					JOptionPane.showMessageDialog(this, "Provided coordinates are invalid. Must be within (0,0) to ("
							+ CellMap.XSIZE + "," + CellMap.YSIZE + ")", "Error", JOptionPane.WARNING_MESSAGE);

				} else {
					Exe.win.repaint();
					validateStartButton();
				}
				break;
			}
			case PULSAR: {
				Number numx = (Number) this.fieldX.getValue(), numy = (Number) this.fieldX.getValue();

				if (!Exe.MAP.buildPulsarAt(numx.intValue(), numy.intValue())) {
					JOptionPane.showMessageDialog(this, "Provided coordinates are invalid. Must be within (0,0) to ("
							+ CellMap.XSIZE + "," + CellMap.YSIZE + ")", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					Exe.win.repaint();
					validateStartButton();
				}
				break;
			}
			case GLIDER: {
				Number numx = (Number) this.fieldX.getValue(), numy = (Number) this.fieldX.getValue();

				if (!Exe.MAP.buildGliderAt(numx.intValue(), numy.intValue())) {
					JOptionPane.showMessageDialog(this, "Provided coordinates are invalid. Must be within (0,0) to ("
							+ CellMap.XSIZE + "," + CellMap.YSIZE + ")", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					Exe.win.repaint();
					validateStartButton();
				}
				break;
			}
			default: {
				// This won't happen... Thanks Java!
				break;
			}
		}

	}

	private void validateStartButton() {
		if (!this.btnStart.isEnabled()) {
			this.btnStart.setEnabled(true);
			btnStart.setToolTipText(null);
		}
	}
	

}
