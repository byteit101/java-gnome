/*
 * Gtk.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.gtk;

/**
 * The GTK widget toolkit initialization and main loop entry point.
 * 
 * @author Andrew Cowie
 */
/*
 * Extremely atypically, this class contains native declarations because a)
 * Theres nothing left if you strip off the Gtk prefix from Gtk, b) no reason to
 * use a static class GtkMain or whatever as none of these methods need access
 * to Plumbing.
 */
public final class Gtk
{

    private static final String APIVERSION = "4.0";

    static {
        System.loadLibrary("gtkjni-" + APIVERSION);
    }

    /**
     * No instantiation. Static methods only!
     */
    private Gtk() {}

    /**
     * A guard against someone calling init() twice
     */
    private static boolean initialized = false;

    /**
     * Initialize the GTK libraries. <b>This must be called before any other
     * org.gnome.* classes are used.</b>
     * 
     * @param args
     *            The command line arguments array. This is passed to the
     *            underlying library to allowing user (or window manager) to
     *            alter GTK's behaviour.
     */
    public static void init(String[] args) {
        if (initialized) {
            return;
        }

        // Glib.init();

        gtk_init(/* args */);

        initialized = true;
    }

    private static native final void gtk_init(/* String[] args? */);

    /**
     * This method blocks, ie, it does not return until the GTK main loop is
     * terminated. If you wish to
     * <p>
     * You can nest calls to Gtk.main()! If you do, then calling
     * {@link #mainQuit() mainQuit()} will make the innermost invocation of the
     * main loop return. (This is how modal Dialog boxes run and block the rest
     * of the application while still accepting events themselves)
     */
    public static void main() {
        gtk_main();
    }

    private static native final void gtk_main();

    public static void mainQuit() {
        gtk_main_quit();
    }

    private static native final void gtk_main_quit();
}
