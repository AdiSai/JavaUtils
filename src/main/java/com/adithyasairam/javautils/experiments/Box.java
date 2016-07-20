package com.adithyasairam.javautils.experiments;

/**
 * Created by Adi on 6/17/2015.
 */

public class Box<E> {
    private int length;
    private int width;
    private int area;
    private E element;
    private transient E[][] items;

    /**
     * Constructor for a Box object.
     *
     * @param l the length of the box.
     * @param w the width of the box.
     */
    public Box(int l, int w) {
        length = l;
        width = w;
        area = l * w;
        items = (E[][]) new Object[l][w];
    }

    /**
     * Returns element type
     *
     * @return element the element type
     */
    @Deprecated
    public E getType() {
        return this.element;
    }

    /**
     * Returns the element at a row, column position.
     *
     * @param r the row position for the object in the box.
     * @param c the column position for the object in the box.
     * @return items[r][c] the item at the row, column position.
     */

    public E get(int r, int c) {
        return items[r][c];
    }

    /**
     * Sets an element object at a row, column position.
     *
     * @param r the row position for the object in the box.
     * @param c the column position for the object in the box.
     * @param obj the object to add to the box.
     */
    public void set(int r, int c, E obj) {
        items[r][c] = obj;
    }

    /**
     * Returns the object at a row, column position.
     *
     * @param r the row position for the object in the box.
     * @param c the column position for the object in the box.
     * @return obj the object at the row, column position.
     */
    public E remove(int r, int c) {
        E obj = items[r][c];
        items[r][c] = null;
        return obj;
    }

    /**
     * Sets the length of the box.
     *
     * @param l the new length for the box.
     */
    public void setLength(int l) {
        length = l;
    }

    /**
     * Sets the width of the box.
     *
     * @param w the new width for the box.
     */
    public void setWidth(int w) {
        width = w;
    }

    /**
     * Returns the length of the box.
     *
     * @return length the length of the box.
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns the width of the box.
     *
     * @return width the width of the box.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the area of the box (length * width).
     *
     * @return area the area of the box.
     */
    public int getArea() {
        return area;
    }

    /**
     * Returns the 2D data array.
     *
     * @return items the array.
     */
    protected E[][] getItems() {
        return items;
    }

    /**
     * Returns a String that represents how a Box should look like.
     * Format:
     * {Item 1, Item 2, Item 3}
     * {Item 4, Item 5, Item 6}
     *
     * @return s the completed String.
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < length; i++) {
            s += "{";
            for (int j = 0; j < width; j++) {
                if (j < (width - 1)) {
                    s += (get(i, j) + ", ");
                } else {
                    s += (get(i, j) + "");
                }
            }
            s += "}";
            s += '\n';
        }
        return s;
    }

    /**
     * Returns an element that has been casted from an object.
     *
     * @param o the object.
     * @param addToBox whether or not to add this newly created element to the box.
     * @return E a casted element.
     * @throws java.lang.ArrayIndexOutOfBoundsException if there is no more space in the box.
     */
    @Deprecated
    public E castAnObjectToTypeE(Object o, boolean addToBox) throws ArrayIndexOutOfBoundsException {
        E tempObj = null;
        try {
            tempObj = ((E) o);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Unable to cast: " + o + " to type: " + element);
        }
        if (addToBox) {
            boolean added = false;
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {
                    if (items[i][j] == null) {
                        items[i][j] = tempObj;
                        added = true;
                        break;
                    }
                }
            }
            if (!(added)) throw new ArrayIndexOutOfBoundsException("Out of space in the Box.");
        }
        return tempObj;
    }
}
