/*
 * MenuItem.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * A MenuItem is the basic item in a Menu.
 * 
 * <p>
 * MenuItems are basic elements, that form a Menu. MenuItems are Containers 
 * and are thus capable of containing other Widgets. Notably, other Menu widgets,
 * allowing a Menu hirearchy. Most other Widgets however, are not very useful
 * when placed in a MenuItem.
 * Only MenuItem widgets and widgets derived from MenuItem widgets are valid
 * children for Menus. This is important, as only they can handle highlighting,
 * alignment and submenus.
 * 
 * <p>
 * MenuItems can be either left justified or right justified. Right justification
 * was initially designed to be used for "Help" Menus, but this is now considered
 * a bad idea. In right to left languages such as Arabic, right justified MenuItems
 * appear on the left instead of the right. 
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 * @author Srichand Pendyala
 * @since 4.0.3
 */
public class MenuItem extends Item
{
    protected MenuItem(long pointer) {
        super(pointer);
    }

    /**
     * Construct a MenuItem
     */
    public MenuItem() {
        super(GtkMenuItem.createMenuItem());
    }

    /**
     * Construct a MenuItem with a given text label. The label may contain
     * underscores (<code>_<code>) which, if present, will indicate
     * the mnemonic which will activate that MenuItem directly if that key is pressed.
     */
    public MenuItem(String mnemonicLabel) {
        super(GtkMenuItem.createMenuItemWithMnemonic(mnemonicLabel));
    }

    /**
     * Sets or replaces the MenuItem's submenu, or removes it if a
     * <code>null</code> Menu is passed.
     */
    public void setSubmenu(Menu child) {
        GtkMenuItem.setSubmenu(this, child);
    }

    /**
     * Returns the submenu underneath this menu item, if any
     * 
     * @return submenu for this menu item, or <code>null</code> if none.
     */
    public Widget getSubmenu() {
        return GtkMenuItem.getSubmenu(this);
    }

    /**
     * The handler interface for an activation. An activation is triggered
     * either when the user clicks the MenuItem, or activates it with the
     * keyboard either by typing that MenuItem's menomic character (if it has
     * one) or selecting the MenuItem via the arrow keys and then pressing
     * <code>&lt;ENTER&gt;</code>.
     */
    public interface ACTIVATE extends GtkMenuItem.ACTIVATE
    {
        public void onActivate(MenuItem sourceObject);
    }

    /**
     * Connect an {@link ACTIVATE} handler to the widget.
     */
    public void connect(ACTIVATE handler) {
        GtkMenuItem.connect(this, handler);
    }
}
