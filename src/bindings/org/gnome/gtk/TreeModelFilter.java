/*
 * TreeModelFilter.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * A TreeModel which can present a subset of its backing model as determined
 * by a filter function. TreeModelFilter acts to wrap an underlying TreeModel.
 * You store your data in this underlying model; the TreeModelFilter just adds
 * the functionality to selectively determine which rows should be visible.
 * 
 * <p>
 * Usage is straight forward. Given the following declarations:
 * 
 * <pre>
 * final ListStore model;
 * final TreeModelFilter filter;
 * final DataColumnInteger elevation;
 * ...
 * </pre>
 * 
 * you initialize and populate your ListStore as usual. To add the filtering
 * functionality, you wrap your ListStore in a TreeModelFilter:
 * 
 * <pre>
 * filter = new TreeModelFilter(model, null);
 * </pre>
 * 
 * then instruct the TreeModelFilter how to select the rows from the concrete
 * TreeModel it is proxying to be included in the virtual model it presents
 * via the
 * {@link #setVisibleCallback(org.gnome.gtk.TreeModelFilter.VISIBLE) setVisibleCallback()}.
 * For instance, if you have a list of all mountains and only want to present
 * peaks higher than 8000 meters, you might do:
 * 
 * <pre>
 * filter.setVisibleCallback(new TreeModelFilter.VISIBLE() {
 *     public boolean onVisible(TreeModelFilter source, TreeModel base, TreeIter row) {
 *         if (base.getValue(row, elevation) &gt; 1000) {
 *             return true;
 *         } else {
 *             return false;
 *         }
 *     }
 * }
 * </pre>
 * 
 * Assuming you are using this data to back a display Widget such as a
 * TreeView, and you only want to present this filtered list of rows, then you
 * use the TreeModelFilter, not the ListStore, as the model backing the
 * TreeView:
 * 
 * <pre>
 * view = new TreeView(filter);
 * </pre>
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public class TreeModelFilter extends TreeModel implements TreeDragSource
{
    protected TreeModelFilter(long pointer) {
        super(pointer);
    }

    /**
     * 
     * @param base
     * @param root
     *            You can give a TreePath to be used as a virtual root so that
     *            the TreeModelFilter only presents and operates on a
     *            subsection of the base TreeModel. This is rarely necessary,
     *            so specify <code>null</code>.
     */
    public TreeModelFilter(TreeModel base, TreePath root) {
        super(GtkTreeModelFilter.createTreeModelFilter(base, root));
    }

    /**
     * The callback invoked when a TreeModelFilter wants to ask if a given row
     * in its child TreeModel should be considered visible in the
     * TreeModelFilter.
     * 
     * <p>
     * Typically when you receive this callback you will reach into the
     * underlying model and query a column by which you will determine whether
     * or not to include this row. This grants the opportunity to put some
     * very complex logic into the VISIBLE callback. We tend to prefer this
     * approach, but if you're rather pre-calculate such states, then you can
     * always add a DataColumnBoolean to the model and simply return the state
     * of that column as the return value from this interface when it is
     * invoked.
     * 
     * <p>
     * <i>If you are researching the GTK API documentation, see
     * <code>(*GtkTreeModelFilterVisibleFunc)</code>. Creating and invoking
     * this "visible" signal is how java-gnome has implemented the function
     * pointer expected by
     * <code>gtk_tree_model_filter_set_visible_func()</code>.</i>
     * 
     * @author Andrew Cowie
     * @since 4.0.6
     */
    /*
     * This is not a real GTK signal! This is a custom hack so we can get the
     * callback using the existing Signal machinery. Note that there is no
     * connect() method.
     */
    public interface VISIBLE extends GtkTreeModelFilter.VISIBLE
    {
        /**
         * Return <code>true</code> for the row to be included in the model,
         * or <code>false</code> for the row to be filtered out.
         * 
         * <p>
         * <b>Warning!</b><br/> <code>row</code> is a valid TreeIter in
         * <code>base</code>, not <code>source</code>. This makes sense
         * if you consider that you will need to ask the underlying proxied
         * TreeModel for information about a row; the only rows you can see in
         * the <code>source</code> TreeModelFilter are, of course, the ones
         * that have passed this test.
         * 
         * @since 4.0.6
         */
        public boolean onVisible(TreeModelFilter source, TreeModel base, TreeIter row);
    }

    public void setVisibleCallback(VISIBLE callback) {
        GtkTreeModelFilterOverride.setVisibleFunc(this);
        GtkTreeModelFilter.connect(this, callback);
    }

    /**
     * Cause the TreeModelFilter to re-calculate whether rows are visible.
     * 
     * @since 4.0.6
     */
    public void refilter() {
        GtkTreeModelFilter.refilter(this);
    }
}
