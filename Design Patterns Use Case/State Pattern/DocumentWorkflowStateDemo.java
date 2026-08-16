interface DocumentState {
    void submitForReview(Document document);
    void approve(Document document);
    void reject(Document document);
    void edit(Document document);
}

class DraftState implements DocumentState {
    @Override
    public void submitForReview(Document document) {
        System.out.println("Draft submitted for review");
        document.setState(new UnderReviewState());
    }

    @Override
    public void approve(Document document) {
        System.out.println("Cannot approve a draft document");
    }

    @Override
    public void reject(Document document) {
        System.out.println("Cannot reject a draft document");
    }

    @Override
    public void edit(Document document) {
        System.out.println("Editing draft document");
    }
}

class UnderReviewState implements DocumentState {
    @Override
    public void submitForReview(Document document) {
        System.out.println("Document is already under review");
    }

    @Override
    public void approve(Document document) {
        System.out.println("Document approved");
        document.setState(new ApprovedState());
    }

    @Override
    public void reject(Document document) {
        System.out.println("Document rejected");
        document.setState(new RejectedState());
    }

    @Override
    public void edit(Document document) {
        System.out.println("Cannot edit a document under review");
    }
}

class ApprovedState implements DocumentState {
    @Override
    public void submitForReview(Document document) {
        System.out.println("Approved document cannot be submitted again");
    }

    @Override
    public void approve(Document document) {
        System.out.println("Document is already approved");
    }

    @Override
    public void reject(Document document) {
        System.out.println("Approved document cannot be rejected");
    }

    @Override
    public void edit(Document document) {
        System.out.println("Approved document cannot be edited");
    }
}

class RejectedState implements DocumentState {
    @Override
    public void submitForReview(Document document) {
        System.out.println("Edit the rejected document before submitting it again");
    }

    @Override
    public void approve(Document document) {
        System.out.println("Rejected document cannot be approved");
    }

    @Override
    public void reject(Document document) {
        System.out.println("Document is already rejected");
    }

    @Override
    public void edit(Document document) {
        System.out.println("Editing rejected document; returning to draft");
        document.setState(new DraftState());
    }
}

class Document {
    private DocumentState curState = new DraftState();

    public void submitForReview() {
        curState.submitForReview(this);
    }

    public void approve() {
        curState.approve(this);
    }

    public void reject() {
        curState.reject(this);
    }

    public void edit() {
        curState.edit(this);
    }

    public void setState(DocumentState newState) {
        if (newState == null) {
            throw new IllegalArgumentException("Document state cannot be null");
        }
        this.curState = newState;
    }
}

public class DocumentWorkflowStateDemo {
    public static void main(String[] args) {
        Document rejectedDocument = new Document();
        rejectedDocument.submitForReview();
        rejectedDocument.reject();
        rejectedDocument.edit();

        Document approvedDocument = new Document();
        approvedDocument.submitForReview();
        approvedDocument.approve();
        approvedDocument.edit();
    }
}
