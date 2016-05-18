<!-- Wrapper-->
<div id="wrapper">

    <!-- Navigation -->
    <div ng-include="'views/navigation'"></div>

    <!-- Page wraper -->
    <!-- ng-class with current state name give you the ability to extended customization your view -->
    <div id="page-wrapper" class="gray-bg {{$state.current.name}}">

        <!-- Page wrapper -->
        <div ng-include="'views/topnavbar'"></div>

        <!-- Main view  -->
        <div ui-view></div>

        <!-- Footer -->
        <div ng-include="'views/footer'"></div>

    </div>
    <!-- End page wrapper-->

</div>
<!-- End wrapper-->