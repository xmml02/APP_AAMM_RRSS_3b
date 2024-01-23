import spacy

def analyze_text(text):
    nlp = spacy.load("es_core_news_sm")
    doc = nlp(text)
    return [(token.text, token.pos_) for token in doc]
