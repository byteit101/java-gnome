/*
 * Widget.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.gtk;

/**
 * The base class of all GTK Widgets. Graphical user interface toolkits have
 * long been built up from individual controls and presentation mechansims that
 * are nested together. These elements are collectively called Widgets. Quite a
 * lot of Widgets contain other Widgets; those are called
 * {@link Container Container}s.
 * 
 * @author Andrew Cowie
 */
public abstract class Widget extends org.gnome.gtk.Object
{

    protected Widget(long pointer) {
        super(pointer);
    }

    /**
     * Cause this Widget to be mapped to the screen. Flags a widget to be
     * displayed. Any widget that isn't shown will not appear on the screen.
     * 
     * <p>
     * There are a bunch of quirks you need to be aware of:
     * 
     * <ul>
     * <li>You have to show the Containers containing a Widget, in addition to
     * the Widget itself, before it will appear on the display.
     * <li>When a toplevel Container is shown (ie, your {@link Window Window}),
     * it is immediately realized and mapped, and any Widgets within it that are
     * shown are then realized and mapped.
     * <li>You can't get information about the actual size that has been
     * allocated to a Widget until it gets mapped to the screen.
     * </ul>
     * 
     * <p>
     * If you want to show all the widgets in a container, it's actually much
     * easier to just call {@link #showAll()} on the container, rather than
     * calling show manually one each individual Widget you've added to it.
     */
    public void show() {
        GtkWidget.show(this);
    }

    /**
     * Cause this Widget, and any Widgets it contains, to be mapped to the
     * screen. You typically call this on a {@link Window Window} after you've
     * finished all the work necessary to set it up.
     * <p>
     * Quite frequently you also want to cause a Window to appear on the screen
     * as well (ie, not be burried under a whole bunch of other applications'
     * Windows), so calling Window's {@link Window#present() present()} is
     * usally next.
     * 
     * <p>
     * <i>Don't be surprised if this takes a few hundred milliseconds. Realizing
     * and mapping all the zillion elements that ultimately make up a Window is
     * one of the most resource intesive operations that GTK, GDK, Pango, your X
     * server, and your kernel have to churn through. Sometimes, you just gotta
     * wait.</i>
     */
    public void showAll() {
        GtkWidget.showAll(this);
    }
}
