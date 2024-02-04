package org.example.Issue;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssuerService {
  @Autowired
  private IssueRepo itemRepo;

  public IssueModel issueBook(IssueRequest issueRequest) {
    IssueModel issueModel = new IssueModel(issueRequest.getBookId(), issueRequest.getReaderId());
    itemRepo.save(issueModel);
    return issueModel;
  }

  public IssueModel getIssueByIdFromService(Long id) {

      IssueModel issueModel = null;
      if (null != (issueModel = itemRepo.findById(id).get())) return issueModel;
      else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
  }

  public List<IssueModel> getAllIssuesFromService() {
      return itemRepo.findAll();
  }

  public IssueModel returnIssueByIdFromService(Long id) {
      IssueModel issueModel = null;
      if (id != null && ((issueModel = itemRepo.findById(id).get()) != null)){
          issueModel.setReturnedAt(LocalDateTime.now());
          return issueModel;
      }
      else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
  }

  public List<IssueModel> getIssuesByReadersIdFromService(long id) {
      return itemRepo.findAllByReaderId(id);
  }

}
