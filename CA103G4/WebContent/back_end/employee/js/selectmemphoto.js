 

    function doClick() {
      var el = document.getElementById("fileElem");
           
      if (el) {  
        el.click();
      }
    }

    function handleFiles() {
    	var file = document.getElementById("fileElem").files[0];
        document.getElementById('img').src = window.URL.createObjectURL(file); 
  }  
    
 
