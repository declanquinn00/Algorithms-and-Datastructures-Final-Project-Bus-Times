	public class Intersection
    {
        //public DLLNode next;
        //public DLLNode prev;
		public int id;
		Street[] from = null;
		Street[] to = null;
    
        /**
         * Constructor
         * 
         */
        public Intersection(int id) 
        {
          this.id = id;
          this.from = null;
          this.to = null;
        }
        
        // Appends a street to an array of streets
        public void appendTo(Street street) {
        	Street[] tmp;
        	// If empty
        	if(to==null) {
        		tmp = new Street[1];
        		tmp[0] = street;
        		to = tmp;
        	}
        	// If not empty
        	else {
	        	tmp = new Street[to.length+1];
	        	System.arraycopy(to, 0, tmp, 0, to.length);
	        	tmp[tmp.length-1] = street; //
	        	to = tmp;
        	}
        }
    }