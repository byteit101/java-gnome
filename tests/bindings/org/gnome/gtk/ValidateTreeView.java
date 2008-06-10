/*
 * ValidateTreeView.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;


/**
 * @author Andrew Cowie
 */
public class ValidateTreeView extends TestCaseGtk
{
    /*
     * This is only here to validate the comments we've made in our API
     * documentation...
     */
    public final void testDefaultSelection() {
        final TreeView view;
        final TreeSelection selection;

        view = new TreeView();
        selection = view.getSelection();

        assertEquals(SelectionMode.SINGLE, selection.getMode());
    }

    public final void testDontAllowAddWithViewport() {
        final TreeView view;
        final ScrolledWindow scroll;

        view = new TreeView();
        scroll = new ScrolledWindow();

        try {
            scroll.addWithViewport(view);
            fail("Should have been prevented");
        } catch (IllegalArgumentException iae) {
            // good
        }
    }
}
