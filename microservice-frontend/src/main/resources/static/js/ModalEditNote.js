
document.addEventListener('DOMContentLoaded', function () {
    var editNoteModal = document.getElementById('editNoteModal');
    editNoteModal.addEventListener('show.bs.modal', function (event) {
        var button = event.relatedTarget;
        var noteId = button.getAttribute('data-note-id');
        var noteContent = button.getAttribute('data-note-content');
        var patientId = button.getAttribute('data-note-patient-id');
        document.getElementById('editNotePatientId').value = patientId;
        document.getElementById('editNoteId').value = noteId;
        document.getElementById('editNoteContent').value = noteContent;
        document.getElementById('editNoteForm').action = '/note/update/' + noteId;
    });
});