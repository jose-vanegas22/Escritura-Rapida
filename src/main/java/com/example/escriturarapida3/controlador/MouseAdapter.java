package com.example.escriturarapida3.controlador;

/**
 * Esta clase implementa los metodos del mouselistener y
 * los crea vacios para que otra clase use los que necesite
 * gracias al abstract
 *
 * @author vaneg
 * @version 1.0
 */
//Esto permite que cuando necesite implementarlo en una clase solo utilice los que necesito
public abstract class MouseAdapter implements MouseListener{
    @Override
    public void mouseClicked() {}
    @Override
    public void mousePressed() {}
    @Override
    public void mouseReleased() {}
    @Override
    public void mouseEntered() {}
    @Override
    public void mouseExited() {}
}
