 

    function doClick() {
      var el = document.getElementById("fileElem");
           
      if (el) {  
        el.click();
      }
    }

    function handleFiles() {
    	
	var d = document.getElementById("fileList");
	var reader = new FileReader();
	var file = document.getElementById("fileElem").files[0];
	var img = document.createElement("img");
	img.src = window.URL.createObjectURL(file);
	img.className= "custom-img";
	img.height = 200;
	img.width = 200;
	d.appendChild(img);
   
	if(file){
		reader.readAsDataURL(file);
	}
	


  }  
    
//    $(document).ready(function (){
//
//        function format_float(num, pos)
//        {
//            var size = Math.pow(10, pos);
//            return Math.round(num * size) / size;
//        }

//        function preview(input) {
//
//            if (input.files && input.files[0]) {
//                var reader = new FileReader();
//                var index = 0//input.name.slice(-1);
//                reader.onload = function (e) {
//                    $('.preview:eq('+index+')').attr('src', e.target.result);
//                    var KB = format_float(e.total / 1024,2);
//                    $('.size:eq('+index+')').text("檔案大小：" + KB + " KB");               
//                }
//
//                reader.readAsDataURL(input.files[0]);
//            }
//
//        }
//
//        $("body").on("change", ".mem_Photo", function (){
//            preview(this);
//        })
//        
    
