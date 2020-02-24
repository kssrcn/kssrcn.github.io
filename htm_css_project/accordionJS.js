var ANIMATION_DURATION = 200;
var ACTIVE_HEIGHT = 93;
var DE_ACTIVE_HEIGHT = 20;
var $currentActiveMenuItem;
var $menuItems;
var $menu;
var $selectMenuItem;
$(document).ready(function () {
    initMenu();
    initEventListener();

    function initMenu() {
        $menuItems = $(".menu_item");
        $menuItems.each(function (index) {
            var $menuItem = $(this);

            $menuItem.data("content", $menuItem.find("div.menu_item_content"));
        })
        
        $menu = $(".accordion_menu");
    }

    function initEventListener() {
        $menuItems.bind("mouseenter", function () {
            if ($currentActiveMenuItem) {
                deactiveMenuItem($currentActiveMenuItem)
            }

            activeMenuItem($(this));

        });
        
        $menuItems.bind("click",function(){
            selectMenuItem($(this));
        });
        
        $menu.bind("mouseleave",function(){
            deactiveMenuItem($currentActiveMenuItem);
            $currentActiveMenuItem = $selectMenuItem;
            
            if($selectMenuItem)
                activeMenuItem($selectMenuItem);
        })
    }
    
    function selectMenuItem($menuItem){
        $selectMenuItem = $menuItem;
    }

    function deactiveMenuItem($menuItem) {
        $menuItem.stop();
        $menuItem.animate({
            height: DE_ACTIVE_HEIGHT
        }, ANIMATION_DURATION, "easeOutQuint");

        var $menuItemContent = $menuItem.data("content");
        $menuItemContent.stop();
        $menuItemContent.animate({
                top: 0
            },
            ANIMATION_DURATION, "easeOutQuint");
    }

    function activeMenuItem($menuItem) {
        $menuItem.stop();
        $menuItem.animate({
            height: ACTIVE_HEIGHT
        }, 200, "easeOutQuint");

        var $menuItemContent = $menuItem.data("content");
        $menuItemContent.stop();
        $menuItemContent.animate({
            top: -DE_ACTIVE_HEIGHT
        }, ANIMATION_DURATION, "easeOutQuint");

        $currentActiveMenuItem = $menuItem;
    }
})
