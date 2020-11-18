package structures;

import java.util.*;

/**
 * This class implements an HTML DOM Tree. Each node of the tree is a TagNode, with fields for
 * tag/text, first child and sibling.
 * 
 */
public class Tree {
	
	/**
	 * Root node
	 */
	TagNode root=null;
	
	/**
	 * Scanner used to read input HTML file when building the tree
	 */
	Scanner sc;
	
	/**
	 * Initializes this tree object with scanner for input HTML file
	 * 
	 * @param sc Scanner for input HTML file
	 */
	public Tree(Scanner sc) {
		this.sc = sc;
		root = null;
	}
	
	/**
	 * Builds the DOM tree from input HTML file, through scanner passed
	 * in to the constructor and stored in the sc field of this object. 
	 * 
	 * The root of the tree that is built is referenced by the root field of this object.
	 */
	public void build() {
		/** COMPLETE THIS METHOD **/
		root= helperBuild();
		
	}
	
	private TagNode helperBuild() {
		String curr= null;
		if (sc.hasNextLine()) {
			curr= sc.nextLine();
		} else {
			return null;
		}
		boolean p = false;
		if (curr.charAt(0)== '<') {
			curr= curr.substring(1, curr.length() -1);
			if (curr.charAt(0)=='/') {
				return null;
			} else {
				p=true;
			}
		}
			TagNode newCurr = new TagNode (curr, null, null);
			if (p==true) {
				newCurr.firstChild=helperBuild();	
			} 
			newCurr.sibling= helperBuild();
			return newCurr;
	}
	
	/**
	 * Replaces all occurrences of an old tag in the DOM tree with a new tag
	 * 
	 * @param oldTag Old tag
	 * @param newTag Replacement tag
	 */
	public void replaceTag(String oldTag, String newTag) {
		/** COMPLETE THIS METHOD **/
		helperReplace(root,oldTag,newTag);
	}
	
	private void helperReplace(TagNode x, String old, String newT2) {
		// Recursive maybe?
		
		if (x==null) {
			return;
		}
		
		if (x.tag.equals(old) && x.firstChild != null) {
			x.tag= newT2;
		}
		
		helperReplace(x.firstChild,old, newT2);
		helperReplace(x.sibling,old,newT2);
		
	}
	
	/**
	 * Boldfaces every column of the given row of the table in the DOM tree. The boldface (b)
	 * tag appears directly under the td tag of every column of this row.
	 * 
	 * @param row Row to bold, first row is numbered 1 (not 0).
	 */
	public void boldRow(int row) {
		/** COMPLETE THIS METHOD **/
		
		TagNode res= helperBoldRow(root); //new TagNode(null,null,null);
		TagNode res2=res.firstChild;
		//res=helperBoldRow(root);
		
		for(int i=1; i< row; i++) {
			res2=res2.sibling;
		}  //traverse through the tree 
		
		for (TagNode br= res2.firstChild; br !=null; br=br.sibling) {
			TagNode rw= new TagNode("b", br.firstChild, null);
			br.firstChild=rw;
		
		}
	}
	
	private TagNode helperBoldRow(TagNode x) {
		//finds the table in the tree
		if (x==null) {
			return null;
		}
		
		if (x.tag.equals("table")) { // if table return the tagnode
			return x;
		}
		
		TagNode one= helperBoldRow(x.sibling); // two new tagnodes for a sibling and firstchild
		TagNode two= helperBoldRow(x.firstChild);
		
		if (one !=null) { // if there is a sibling return 
			return one;
		}
		
		if(two!=null) { // if there is a first child
			return two;
		}
		return null;
	}
	
	
	/**
	 * Remove all occurrences of a tag from the DOM tree. If the tag is p, em, or b, all occurrences of the tag
	 * are removed. If the tag is ol or ul, then All occurrences of such a tag are removed from the tree, and, 
	 * in addition, all the li tags immediately under the removed tag are converted to p tags. 
	 * 
	 * @param tag Tag to be removed, can be p, em, b, ol, or ul
	 */
	public void removeTag(String tag) {
		/** COMPLETE THIS METHOD **/
		if ((tag.equals("b")) || (tag.equals("em")) || (tag.equals("p"))) {
			helperRemoveTag1(root,tag);
		}
		
		if ((tag.equals("ul")) || (tag.equals("ol"))) {
			helperRemoveTag2(root,tag);
		}
		
	}
	
	
	private void helperRemoveTag1(TagNode res, String x ) {
		// delete tags that have p, em or b
		if (res==null) {
			return;
		}
	
		if (res.tag.equals(x) && res.firstChild !=null) {
			// if Tag equals p, em or b and it has a first child
			res.tag = res.firstChild.tag;
			//remove tag and move the it first child is new tag
				if (res.firstChild.sibling != null) {
				//if first child has a sibling
				TagNode p = new TagNode(null,null,null);
					for (p= res.firstChild; p.sibling !=null; p=p.sibling);
						p.sibling=res.sibling;
						res.sibling=res.firstChild.sibling;
					// make into while statement
			}
			
				res.firstChild=res.firstChild.firstChild;
		}
		
		helperRemoveTag1(res.firstChild, x);
		helperRemoveTag1(res.sibling, x);
		
		
	}	
	
	private void helperRemoveTag2(TagNode res, String x) {
		if (res==null) {
			return;
		}
		
		if(res.tag.equals(x) && res.firstChild != null) {
			res.tag = "p";
			TagNode lol = null;
				
			for (lol=res.firstChild; lol.sibling !=null; lol=lol.sibling) {
					lol.tag="p";
				}
					lol.tag="p";
					lol.sibling=res.sibling;
					res.sibling=res.firstChild.sibling;
					res.firstChild=res.firstChild.firstChild;
		}
		
		helperRemoveTag2(res.firstChild,x); {
		helperRemoveTag2(res.sibling,x);
		}
	}
	
/**
 * 
 */
	
	/**
	/**
	 * Adds a tag around all occurrences of a word in the DOM tree.
	 * 
	 * @param word Word around which tag is to be added
	 * @param tag Tag to be added
	 */
	public void addTag(String word, String tag) {
		/** COMPLETE THIS METHOD **/
			if (tag.equals("em") || tag.equals("b")) { 
				root = helperAddTag(word, tag, root);
			}
		}


	private TagNode helperAddTag(String word, String tag, TagNode res) {
		if(res==null) {
			return null;
		}
	
		if (res.sibling != null) {
			res.sibling = helperAddTag(word, tag, res.sibling);
		}
		
		if (res.firstChild != null) {
			res.firstChild = helperAddTag(word, tag, res.firstChild);
		}
		
		if (res.tag.contains(word)==true) {
			String rem = res.tag;
			TagNode nde;
			TagNode ptr;
			String remci= rem.toLowerCase();
			String wordl = word.toLowerCase();
			int i= remci.indexOf(wordl);
			//check for punctuation 
			//check for if 'word' is part of another word
			
			
		
			if (i==0) {
				String target = rem.substring(0,word.length());
				rem = rem.substring(word.length());
				TagNode tgt=new TagNode(target, null , null);
				TagNode sib= new TagNode(rem, null,null);
				 nde= new TagNode(tag, tgt, sib);
				 ptr=nde.sibling;
			} else {
				 nde= new TagNode(rem.substring(0,i),null, null);
				 rem= rem.substring(i);
				 String target = rem.substring(0,word.length());
				 TagNode tgt=new TagNode(target, null , null);
				 rem = rem.substring(word.length());
				 TagNode sib= new TagNode(rem, null,null);
				 nde.sibling= new TagNode(tag, tgt, sib);
				 ptr=nde.sibling.sibling;
			}
			
			remci= rem.toLowerCase();
			while(remci.contains(wordl)) {
				i=remci.indexOf(wordl);
				 rem= rem.substring(i);
				 String target = rem.substring(0,word.length());
				 TagNode tgt=new TagNode(target, null , null);
				 rem = rem.substring(word.length());
				 TagNode sib= new TagNode(rem, null,null);
				 ptr.sibling= new TagNode(tag, tgt, sib);
				 ptr=ptr.sibling.sibling;
				 remci= rem.toLowerCase();

				
			}
			return nde;
		}
			//TagNode nde = new TagNode(tag, res, res.sibling);
			//res.sibling = null;
			//return nde;
		
		
		return res; 
		
	}
	
	
	/**
	 * Gets the HTML represented by this DOM tree. The returned string includes
	 * new lines, so that when it is printed, it will be identical to the
	 * input file from which the DOM tree was built.
	 * 
	 * @return HTML string, including new lines. 
	 */
	public String getHTML() {
		StringBuilder sb = new StringBuilder();
		getHTML(root, sb);
		return sb.toString();
	}
	
	private void getHTML(TagNode root, StringBuilder sb) {
		for (TagNode ptr=root; ptr != null;ptr=ptr.sibling) {
			if (ptr.firstChild == null) {
				sb.append(ptr.tag);
				sb.append("\n");
			} else {
				sb.append("<");
				sb.append(ptr.tag);
				sb.append(">\n");
				getHTML(ptr.firstChild, sb);
				sb.append("</");
				sb.append(ptr.tag);
				sb.append(">\n");	
			}
		}
	}
	
	/**
	 * Prints the DOM tree. 
	 *
	 */
	public void print() {
		print(root, 1);
	}
	
	private void print(TagNode root, int level) {
		for (TagNode ptr=root; ptr != null;ptr=ptr.sibling) {
			for (int i=0; i < level-1; i++) {
				System.out.print("      ");
			};
			if (root != this.root) {
				System.out.print("|---- ");
			} else {
				System.out.print("      ");
			}
			System.out.println(ptr.tag);
			if (ptr.firstChild != null) {
				print(ptr.firstChild, level+1);
			}
		}
	}
}
