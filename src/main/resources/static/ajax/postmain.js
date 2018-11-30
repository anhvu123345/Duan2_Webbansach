
							$(document).ready(function () {

							    $("#formanh1").submit(function (event) {
							    	 event.preventDefault();
							    	 
							    	 var form = $('#formanh1')[0];
							    	 
							    	    var data = new FormData(form);
							    	 
							   
							    	fire_ajax_submit1();
							    });});
							$(document).ready(function () {
							    $("#formanh2").submit(function (event) {
							    	 event.preventDefault();
							    	 
							    	 var form = $('#formanh2')[0];
							    	 
							    	    var data = new FormData(form);
							    	 
							   
							    	fire_ajax_submit2();
							    });  });
							$(document).ready(function () {
							    $("#formanh3").submit(function (event) {
							    	 event.preventDefault();
							    	 
							    	 var form = $('#formanh3')[0];
							    	 
							    	    var data = new FormData(form);
							    	 
							   
							    	fire_ajax_submit3();
							    });});
							$(document).ready(function () {
							    $("#formanh4").submit(function (event) {
							    	 event.preventDefault();
							    	 
							    	 var form = $('#formanh4')[0];
							    	 
							    	    var data = new FormData(form);
							    	 
							   
							    	fire_ajax_submit4();
							    });
							});
							function fire_ajax_submit1() {
								
								var form = $('#formanh1')[0];
						    	 
					    	    var data = new FormData(form);
							    $.ajax({
							    	type :  "POST",
							    	method :  "POST",
							        url: "/manager/upload1",
							        data : data,
							        processData: false,
							        contentType: false,
							        cache: false,
							        timeout: 1000000,
							        success:data
							    });
							    		
							}
							function fire_ajax_submit2() {
								
								var form = $('#formanh2')[0];
						    	 
					    	    var data = new FormData(form);
							    $.ajax({
							    	type :  "POST",
							    	method :  "POST",
							        url: "/manager/upload2",
							        data : data,
							        processData: false,
							        contentType: false,
							        cache: false,
							        timeout: 1000000,
							        success:data
							    });
							    		
								}
								function fire_ajax_submit3() {
																
								var form = $('#formanh3')[0];
						    	 
					    	    var data = new FormData(form);
							    $.ajax({
							    	type :  "POST",
							    	method :  "POST",
							        url: "/manager/upload3",
							        data : data,
							        processData: false,
							        contentType: false,
							        cache: false,
							        timeout: 1000000,
							        success:data
							    });
							    		
							}
								function fire_ajax_submit4() {
									
									var form = $('#formanh4')[0];
									 
								    var data = new FormData(form);
								    $.ajax({
								    	type :  "POST",
								    	method :  "POST",
								        url: "/manager/upload4",
								        data : data,
								        processData: false,
								        contentType: false,
								        cache: false,
								        timeout: 1000000,
								        success:data
								    });
								}
								
								function myClickButton(){
									
								
									var productid = parseInt(document.getElementById("product.productid").value);
									if (document.getElementById("filea[0]").value != "" ) {
										$("#formanh1").submit();
									
									}
									 if (document.getElementById("fileb[0]").value != "") {
								
										$( "#formanh2").submit();
									}
									 if (document.getElementById("filec[0]").value != "" ) {
										$("#formanh3").submit();
									
									}
									 if (document.getElementById("filed[0]").value != "") {
										$("#formanh4").submit();
									}
									if (document.getElementById("filea[0]").value != "" && document.getElementById("filec[0]").value != "") {
										$("#formanh1").submit();
										$( "#formanh3").submit();
									}
									 if (document.getElementById("filea[0]").value != "" && document.getElementById("fileb[0]").value != "") {
										$("#formanh1").submit();
										$( "#formanh2").submit();
									}
									 if (document.getElementById("filea[0]").value != "" && document.getElementById("filed[0]").value != "") {
										$("#formanh1").submit();
										$( "#formanh4").submit();
									}
									 if (document.getElementById("fileb[0]").value != "" && document.getElementById("filec[0]").value != "") {
										$("#formanh2").submit();
										$( "#formanh3").submit();
									}
									 if (document.getElementById("fileb[0]").value != "" && document.getElementById("filed[0]").value != "") {
										$("#formanh2").submit();
										$("#formanh4").submit();
									}
									 if (document.getElementById("filec[0]").value != "" && document.getElementById("filed[0]").value != "") {
										$("#formanh3").submit();
										$("#formanh4").submit();
									}
									 if (document.getElementById("filea[0]").value != "" && document.getElementById("fileb[0]").value != "" && document.getElementById("filec[0]").value != "") {
										
										$("#formanh1").submit();
										$("#formanh2").submit();
										$("#formanh3").submit();
									}
									 if (document.getElementById("filea[0]").value != "" && document.getElementById("fileb[0]").value != "" && document.getElementById("filed[0]").value != "") {
										$("#formanh1").submit();
										
										$("#formanh2").submit();
									
										$("#formanh4").submit();
									}
									 if (document.getElementById("fileb[0]").value != "" && document.getElementById("filec[0]").value != "" && document.getElementById("filed[0]").value != "") {
										$("#formanh2").submit();
								
										$("#formanh3").submit();
										
										$("#formanh4").submit();
									}
									 if (document.getElementById("filea[0]").value != "" && document.getElementById("filec[0]").value != "" && document.getElementById("filed[0]").value != "") {
										$("#formanh1").submit();
									
										$("#formanh3").submit();
									
										$("#formanh4").submit();
									}
						/*			
						 if (document.getElementById("fileb[0]").value != "") {
										 $("#submit2").click();
									}

									if (document.getElementById("filec[0]").value != "") {
										$("#submit3").click();
									}
									if (document.getElementById("filed[0]").value != "") {
										$("#submit4").click();
									}*/
										swal({
											text : 'Thêm Thành công!',
											buttons : false,
											icon: "success",
											showCancelButton : false,
											showConfirmButton : false
										}).then (function(){
										    window.location = "/manager/"+productid+"/upload";
											
										});
										
								};
						
							
							
						
							