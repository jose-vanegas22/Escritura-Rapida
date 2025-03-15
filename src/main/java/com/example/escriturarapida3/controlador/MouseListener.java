package com.example.escriturarapida3.controlador;

/**
 *Esta interfaz define todos los metodos que deben de ser
 * implementados por cualquier clase que quiera responder a
 * eventos del mouse
 *
 * @author vaneg
 * @version 1.0
 */
public interface MouseListener {
    void mouseClicked();
    void mousePressed();
    void mouseReleased();
    void mouseEntered();
    void mouseExited();
}
