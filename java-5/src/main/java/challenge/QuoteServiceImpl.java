package challenge;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	private QuoteRepository repository;

	@Override
	public Quote getQuote() {
		return repository.findQuote();
	}

	@Override
	public Quote getQuoteByActor(String actor) {
		return repository.findByActor(actor);
	}

}
