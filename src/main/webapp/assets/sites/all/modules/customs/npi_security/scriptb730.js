
// date 24-2-2016   code for add class in mega menu  written by waliullah 


// code end

 function burstCache() {
	var url = window.location.href;
	if(base_url != url && base_url+"/" != url){
		if (!navigator.onLine) {
			document.body.innerHTML = "Loading...";
			window.location = "/";
		}
	}
}
window.onload = burstCache;






