function toggleHBFormSettings(unqid){
	
	unqfrmid=jQuery('#'+unqid+" form").attr("id");
	
	if(jQuery('#'+unqfrmid+" .form-item-tname").css('display')=='none')
	{
		if(jQuery('#'+unqid+" .hbFrmSettings").css('display')=='none' )
		 { 
		 
			 jQuery('#'+unqid+" .hbFrmSettings").show();
			 jQuery('#'+unqid+" .hbFrmListing").hide(); 
			 jQuery('#'+unqfrmid+" .form-item-state").show();
			 jQuery('#'+unqfrmid+" .form-item-order-by").show();
			 jQuery('#'+unqfrmid+" .form-item-tname").hide(); 
		 }
		 else
		 { 
			 jQuery('#'+unqid+" .hbFrmSettings").hide();
			 jQuery('#'+unqid+" .hbFrmListing").show();
			 jQuery('#'+unqfrmid+" .form-item-state").hide();
			 jQuery('#'+unqfrmid+" .form-item-order-by").hide();
			 jQuery('#'+unqfrmid+" .form-item-tname").hide(); 
		 }
	}
	else
	{
		 jQuery('#'+unqid+" .hbFrmListing").hide();
		 jQuery('#'+unqfrmid+" .form-item-state").show();
		 jQuery('#'+unqfrmid+" .form-item-order-by").show();
		 jQuery('#'+unqfrmid+" .form-item-tname").hide();
	}
}	


function toggleHBFormSearch(unqid){
	 
	unqfrmid=jQuery('#'+unqid+" form").attr("id");
	if(jQuery('#'+unqfrmid+" .form-item-state").css('display')=='none')
	{
		 if(jQuery('#'+unqid+" .hbFrmSettings").css('display')=='none' )
		 { 
			 jQuery('#'+unqid+" .hbFrmSettings").show();
			 jQuery('#'+unqid+" .hbFrmListing").hide();
			 jQuery('#'+unqfrmid+" .form-item-state").hide();
			 jQuery('#'+unqfrmid+" .form-item-order-by").hide();
			 jQuery('#'+unqfrmid+" .form-item-tname").show();
			 
		 }
		 else
		 { 
			 jQuery('#'+unqid+" .hbFrmSettings").hide();
			 jQuery('#'+unqid+" .hbFrmListing").show();
			 jQuery('#'+unqfrmid+" .form-item-state").hide();
			 jQuery('#'+unqfrmid+" .form-item-order-by").hide();
			 jQuery('#'+unqfrmid+" .form-item-tname").hide();
		 }
		
	}
	else
	{

		 jQuery('#'+unqid+" .hbFrmListing").hide();
		 jQuery('#'+unqfrmid+" .form-item-state").hide();
		 jQuery('#'+unqfrmid+" .form-item-order-by").hide();
		 jQuery('#'+unqfrmid+" .form-item-tname").show();

	}
	 
}

jQuery(document).ready(function(){
								
								
			jQuery(".hbFrmListing").show();						 
			jQuery(".hbFrmSettings").hide();
			jQuery(".hbFrmSettings .form-item-state").hide();
			jQuery(".hbFrmSettings .form-item-order-by").hide();
			jQuery(".hbFrmSettings .form-item-tname").hide();					 
			 jQuery(".persHBFrm").live("click", function(event){								   
				event.preventDefault();
				unqid=jQuery(this).parents().eq(3).attr("id"); // find unique id of elcosing div, there may be multiple blocks of same type
				toggleHBFormSettings(unqid); 
			 });
			 jQuery(".srchHBFrm").live("click", function(event){								   
				event.preventDefault();
				unqid=jQuery(this).parents().eq(3).attr("id"); // find unique id of elcosing div, there may be multiple blocks of same type
				toggleHBFormSearch(unqid); 
				
			 });				 
});


function toggleHBFormsResult(){
	
	jQuery(".form-item-state").hide();
	jQuery(".form-item-order-by").hide();
	jQuery(".form-item-tname").hide();
	jQuery(".hbFrmSettings").hide();
	jQuery(".hbFrmListing").show();
	jQuery(".hbItemList").show();// for all boxes show listing if their search form is opened
	jQuery(".hbFiltering").hide(); // for all boxes hide form if their search form is opened
}


(function ($) {	   
  Drupal.behaviors.hb_forms = {
    attach: function (context, settings) {
      // Your Javascript code goes here
		//$(document).ready(function(){
						
				$.fn.hbShowFrmListing = function(data, state, order_by, title) {
					//alert(title);
					//alert(state);
					$('.hbFrmListing').html(data);
					$(".hb-frm-state-bx").val(state);  // set all box values to same in case of multiple boxes
					$(".hb-frm-order_by-bx").val(order_by);
					$(".hb-frm-tname-bx").val(title);
					toggleHBFormsResult();  
				}	
		//});

    }
	
  };
}(jQuery));

