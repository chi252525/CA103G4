 

    function doClick() {
      var el = document.getElementById("fileElem");
           
      if (el) {  
        el.click();
      }
    }

    function handleFiles(files) {
        var d = document.getElementById("fileList");
        if (!files.length) {
            d.removeChild(img);
            }else {

                var img = document.createElement("img");
                img.src = window.URL.createObjectURL(files[0]);
                img.className= "custom-img";
                img.height = 200;
                img.width = 200;
                img.onload = function() {
                window.URL.revokeObjectURL(this.src);
            }
        d.appendChild(img);

        }
    }
