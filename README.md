# DOM-Tree
Java Program that builds a Document Object Model tree from a HTML file

Methods:

    public class Tree {
        public Tree(Scanner sc)                         //Initializes a new scanner with which to build tree in build()
        public void build()                             //Recursively builds tree 
        public void replaceTag(String old, String new)  //Replaces all occurrences of old tag with new tag
        public void boldRow (int row)                   //bold the entire specified row
        public void removeTag(String tag)               //Recursively removes all instances of tag
        public void addTag(String word, String tag)     //Recursively Adds tag to all instances of word
        public String getHTML()                         //Return HTML representation of tree
        }
