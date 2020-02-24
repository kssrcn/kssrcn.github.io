 var BANNER_WIDTH = 980;
 var SHOW_DURATION = 500;
 var $banner_content;
 var nBannerLength = 0;
 var nCurrentBannerIndex = 0;
 var $banner_dots;
var autoTimerID;
 $(document).ready(function () {
     initMenu();
     initEventListener();
     startAutoPlay();
 });

 function initMenu() {
     $banner_content = $("#banner_content");
     nBannerLength = $banner_content.children("img").length;
     $banner_content.width(BANNER_WIDTH * nBannerLength);

     $banner_dots = $("#banner_nav li a");
     showBannerDoAt(0);
     
     autoTimerID = 0;
 }

 function initEventListener() {
     $("#btn_prev_banner").bind("click", function () {
         prevBanner();
     });
     $("#btn_next_banner").bind("click", function () {
         nextBanner();
     });

     $banner_dots.bind("mouseenter", function () {
         var nIndex = $banner_dots.index(this);
         showBannerDoAt(nIndex);
         showBannerAt(nIndex);
     })
     
     var $banner_slider = $("div.banner_slider");
     
     $banner_slider.bind("mouseenter",function(){
         stopAutoPlay();
     });
     
     $banner_slider.bind("mouseleave",function(){
         startAutoPlay();
     });
 }

 function prevBanner() {
     var nIndex = nCurrentBannerIndex - 1;

     if (nIndex < 0)
         nIndex = nBannerLength - 1;

     this.showBannerAt(nIndex);
 }

 function nextBanner() {
     var nIndex = nCurrentBannerIndex + 1;
     if (nIndex >= nBannerLength)
         nIndex = 0;

     this.showBannerAt(nIndex);
 }

 function showBannerAt(nIndex) {
     if (nIndex != this.nCurrentBannerIndex) {
         var nPosition = -BANNER_WIDTH * nIndex;

         $banner_content.stop();
         $banner_content.animate({
             left: nPosition
         }, SHOW_DURATION, "easeOutQuint");
         this.nCurrentBannerIndex = nIndex;
     }
 }

 function showBannerDoAt(nIndex) {
     this.$banner_dots.eq(this.nCurrentBannerIndex).removeClass("select");
     this.$banner_dots.eq(nIndex).addClass("select");
 }

function startAutoPlay(){
    if(this.autoTimerID!=0)
        clearInterval(this.autoTimerID);
    
    this.autoTimerID = setInterval(function(){
        nextBanner();
    },3000);
}

function stopAutoPlay(){
    if(this.autoTimerID!=0){
        clearInterval(this.autoTimerID);
    }
    
    this.autoTimerID = 0;
}
