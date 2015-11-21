<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value="/resources/css/jquery-ui.css"/>" rel="stylesheet"/>
<t:template>

    <input type="hidden" value="${pageContext.request.contextPath}" id="context-path">

    <div class="friends-list">

        <div class="buttons-group">
            <button class="btn btn-default" type="button" id="btn-refresh">Refresh</button>
            <button class="btn btn-default" type="button" id="btn-edit" data-target='#edit-modal'
                    data-toggle='modal'>Edit
            </button>
            <button class="btn btn-default" type="button" id="btn-delete">Delete</button>
        </div>

        <table class="table">
            <thead class="focus-color">
            <tr>
                <th>Name</th>
                <th>Genre</th>
                <th>Year</th>
                <th>Mark</th>
                <th>Date of watch</th>
                <th>Review</th>
                <th></th>
            </tr>
            </thead>

            <tbody id="films"></tbody>
        </table>

    </div>

    <div class="modal fade" id="edit-modal" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" id="btn-close" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Edit films</h4>
                </div>

                <div class="modal-body">
                    <div class="alert alert-danger non-visible" id="error" role="alert"></div>

                    <form id="modal-text">
                        <div class="form-group">
                            <label for="film-name">Name:</label>
                            <input type="text" class="form-control" id="film-name" autofocus>
                        </div>
                        <div class="form-group">
                            <label for="film-genre">Genre:</label>
                            <input type="text" class='form-control' id="film-genre">
                        </div>
                        <div class="form-group">
                            <label for="film-year">Year:</label>
                            <input type="text" class="form-control" id="film-year">
                        </div>
                        <div class="form-group">
                            <label for="film-mark">Mark:</label>
                            <input type="number" class="form-control" min="1" max="10" step="1" id="film-mark">
                        </div>
                        <div class="form-group">
                            <label for="film-date-seen">Date seen:</label>
                            <input type="text" class="form-control" id="film-date-seen" readonly>
                        </div>
                        <div class="form-group">
                            <label for="film-review">Review:</label>
                            <textarea class="form-control" rows="3" id="film-review"></textarea>
                        </div>

                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="btn-save">Save</button>
                    <button type="button" class="btn btn-default" id="btn-add">Add</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="btn-cancel">Cancel</button>
                </div>
            </div>
        </div>
    </div>

</t:template>
<script src="<c:url value="/resources/js/validation.js"/>"></script>
<script src="<c:url value="/resources/js/main.js"/>"></script>
<script src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.tmpl.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.tmpl.min.js"/>"></script>
