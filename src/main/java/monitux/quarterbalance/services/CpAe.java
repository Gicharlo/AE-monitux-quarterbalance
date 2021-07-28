package monitux.quarterbalance.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CpAe {

	private Set<String> errorInf = new HashSet<>();

	public void setErrorInf(Set<String> errorInf) {
		this.errorInf = errorInf;
	}

	public Set<String> getErrorInf() {
		return errorInf;
	}

	public void addtErrorInf(String errorInf) {
		this.errorInf.add(errorInf);
	}

	public void showInf() {
		this.errorInf.forEach((error) -> {
			System.out.println(error);
		});
	}

	public void saveXml(Map<Integer, String> companies, String filePath, String indCon, String year)
			throws IOException {

		companies.forEach((id, company) -> {

			if (id < 2000) {

				String auxUrl = "http://cp.ae.com.br/aefundamental/v1/demonstracao/detalhado?10065=xml&10023=4&10039=ae&13003=v1&13004="
						+ id + "&13005=B&13006=" + indCon + "&13007=T&13008=" + year;

				try {

					URL url = new URL(auxUrl);

					File file = new File(filePath + id + ".xml");

					BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
					BufferedWriter out = new BufferedWriter(new FileWriter(file));

					String LinePage;

					while ((LinePage = in.readLine()) != null) {

						out.write(LinePage);
						out.newLine();
					}

					in.close();
					out.flush();
					out.close();

				} catch (IOException e) {
					System.out.println(e.getMessage());
				}

			}
		});

		/*
		 * 13004=9512 (Código CVM da Empresa) 13005=DRE (DRE = Resultado de exercício
		 * ,B= Balanço, FC = Fluxo de caixa) 13006=I (I = Individual, C = Consolidado)
		 * 13007=T (T = Trimestral, A = Anual) 13008=2020 (Ano) 13010=5 = (Períodos,
		 * geralmente vai 5, acho que nem precisa passar, só vai quando é anual)
		 */

	}

	public void readingXml(File quarter, Map<Integer, Map<Set<String>, Set<String>>> xml)
			throws ParserConfigurationException, SAXException, IOException {

		for (File file : quarter.listFiles()) {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			String nameFile = file.getName();
			int aux2 = Integer.parseInt(nameFile.replace(".xml", ""));

			NodeList DATA_EXERCICIOList = doc.getElementsByTagName("DATA_EXERCICIO");

			NodeList CODIGO_CONTAList = doc.getElementsByTagName("CODIGO_CONTA");

			Set<String> auxQuarters = new HashSet<>();
			Set<String> auxIndCon = new HashSet<>();

			for (int i = 0; i < DATA_EXERCICIOList.getLength(); i++) {
				Element quartersElement = (Element) DATA_EXERCICIOList.item(i);
				Element indConElement = (Element) CODIGO_CONTAList.item(i);

				String quarters = quartersElement.getTextContent();
				String indCon = indConElement.getTextContent();

				auxQuarters.add(quarters);
				auxIndCon.add(indCon);

				xml.put(aux2, new HashMap<Set<String>, Set<String>>());
				xml.get(aux2).put(auxIndCon, auxQuarters);
			}
		}

	}

	public void validateFirstQuarterInd(Map<Integer, String> cvm, Map<Integer, Map<Set<String>, Set<String>>> xml,
			String auxIndConN, String auxIndConL2, String tr) {

		try {

			if (!cvm.isEmpty()) {

				xml.forEach((idX, exerciseDate) -> {
					exerciseDate.forEach((indCon, quarter) -> {

						if (idX < 2000) {

							String value;

							if (quarter.contains("1T" + tr)) {

								if (indCon.contains(auxIndConN)) {

									if (cvm.containsKey(idX)) {
										value = (String) cvm.get(idX);

										System.out.println("A empresa " + value + ", com o id: " + idX
												+ ", possui o Ind " + auxIndConL2 + " - 1T" + tr + ", no Broadcast.");
										errorInf.add(value + "salvando 1T - In" + tr);

									} else {
										System.out.println("Não foi encontrado o nome da empresa: " + idX
												+ ", possui o Ind " + auxIndConL2 + " - 1T" + tr + ", no Broadcast.");
										errorInf.add("salvando 1T - In" + tr);
									}

								} else {
									if (cvm.containsKey(idX)) {
										value = (String) cvm.get(idX);

										System.out.println(
												"A empresa " + value + ", com o id:" + idX + ", não possui Ind "
														+ auxIndConL2 + " - 1T" + tr + ", no Broadcast, como deveria.");

										errorInf.add(value + " - 1T " + tr + "Ind - " + auxIndConN + " (NÃO POSSUI)");

									} else {
										System.out.println("Não foi encontrado o nome da empresa: " + idX
												+ ", e a mesma não possui Ind" + auxIndConL2 + " - 1T" + tr
												+ ", no Broadcast, como deveria.");
										errorInf.add("1T " + tr + "Ind - " + auxIndConN + " (NÃO POSSUI)");
									}
								}

							} else {
								if (cvm.containsKey(idX)) {
									value = (String) cvm.get(idX);

									System.out.println("A empresa " + value + ", com o id:" + idX
											+ ", não possui os dados do trimestre selecionado(Ind " + auxIndConL2
											+ " - 1T" + tr + "), como deveria.");
									errorInf.add(value + " - 1T " + tr + " (NÃO POSSUI) \n");
								} else {
									System.out.println("Não foi encontrado o nome da empresa: " + idX
											+ ", e a mesma não possui os dados do trimestre selecionado(Ind "
											+ auxIndConL2 + " - 1T" + tr + "), como deveria.");
									errorInf.add("1T " + tr + " (NÃO POSSUI) \n");
								}
							}

						}

					});
				});

			} else {
				System.out.println(
						"Não foi realizadas chamadas na Cp, pois a CVM não publicou dados p/o período selecionado (Ind "
								+ auxIndConL2 + " - 1T" + tr + ").");
			}
			System.out.println();
			System.out.println();

		} catch (Exception e) {
			e.getMessage();

		}

	}

	public void validateFirstQuarterCon(Map<Integer, String> cvm, Map<Integer, Map<Set<String>, Set<String>>> xml,
			String auxIndConN, String auxIndConL2, String tr) {

		System.out.println();

		if (!cvm.isEmpty()) {
			xml.forEach((idX, exerciseDate) -> {
				exerciseDate.forEach((indCon, quarter) -> {

					if (idX < 2000) {

						String value;

						if (quarter.contains("1T" + tr)) {

							if (indCon.contains(auxIndConN)) {

								if (cvm.containsKey(idX)) {
									value = (String) cvm.get(idX);

									System.out.println("A empresa " + value + ", com o id: " + idX + ", possui o Con "
											+ auxIndConL2 + " - 1T" + tr + ", no Broadcast.");
									errorInf.add(value + "salvando 1T - Con" + tr);
								} else {
									System.out.println("Não foi encontrado o nome da empresa: " + idX
											+ ", possui o Con " + auxIndConL2 + " - 1T" + tr + ", no Broadcast.");
									errorInf.add("salvando 1T - Con" + tr);
								}

							} else {
								if (cvm.containsKey(idX)) {
									value = (String) cvm.get(idX);

									System.out.println("A empresa " + value + ", com o id:" + idX + ", não possui Con "
											+ auxIndConL2 + " - 1T" + tr + ", no Broadcast, como deveria.");
									errorInf.add(value + " - 1T " + tr + "Con - " + auxIndConN + " (NÃO POSSUI)");
								} else {
									System.out.println("Não foi encontrado o nome da empresa: " + idX
											+ ", e a mesma não possui Con" + auxIndConL2 + " - 1T" + tr
											+ ", no Broadcast, como deveria.");
									errorInf.add("1T " + tr + "Con - " + auxIndConN + " (NÃO POSSUI)");
								}
							}

						} else {
							if (cvm.containsKey(idX)) {
								value = (String) cvm.get(idX);

								System.out.println("A empresa " + value + ", com o id:" + idX
										+ ", não possui os dados do trimestre selecionado(Con " + auxIndConL2 + " - 1T"
										+ tr + "), como deveria.");
								errorInf.add(value + " - 1T " + tr + " (NÃO POSSUI)");
							} else {
								System.out.println("Não foi encontrado o nome da empresa: " + idX
										+ ", e a mesma não possui os dados do trimestre selecionado(Con " + auxIndConL2
										+ " - 1T" + tr + "), como deveria.");
								errorInf.add("1T " + tr + " (NÃO POSSUI) \n");
							}
						}
					}

				});
			});
		} else {
			System.out.println(
					"Não foi realizadas chamadas na Cp, pois a CVM não publicou dados p/o período selecionado (Con "
							+ auxIndConL2 + " - 1T" + tr + ").");
		}

		System.out.println();
		System.out.println();

	}

	public void validateSecondQuarterInd(Map<Integer, String> cvm, Map<Integer, Map<Set<String>, Set<String>>> xml,
			String auxIndConN, String auxIndConL2, String tr) {

		System.out.println();

		if (!cvm.isEmpty()) {
			xml.forEach((idX, exerciseDate) -> {
				exerciseDate.forEach((indCon, quarter) -> {

					if (idX < 8000) {

						String value;

						if (quarter.contains("2T" + tr)) {

							if (indCon.contains(auxIndConN)) {

								if (cvm.containsKey(idX)) {
									value = (String) cvm.get(idX);

									System.out.println("A empresa " + value + ", com o id: " + idX + ", possui o In "
											+ auxIndConL2 + " - 2T" + tr + ", no Broadcast.");
									errorInf.add(value + "salvando 2T - Con" + tr);
								} else {
									System.out.println("Não foi encontrado o nome da empresa: " + idX + ", possui o In "
											+ auxIndConL2 + " - 2T" + tr + ", no Broadcast.");
									errorInf.add("salvando 2T - Con" + tr);

								}

							} else {
								if (cvm.containsKey(idX)) {
									value = (String) cvm.get(idX);

									System.out.println("A empresa " + value + ", com o id:" + idX + ", não possui In "
											+ auxIndConL2 + " - 2T" + tr + ", no Broadcast, como deveria.");
									errorInf.add(value + " - 2T " + tr + "Con - " + auxIndConN + " (NÃO POSSUI)");
								} else {
									System.out.println("Não foi encontrado o nome da empresa: " + idX
											+ ", e a mesma não possui In " + auxIndConL2 + " - 2T" + tr
											+ ", no Broadcast, como deveria.");
									errorInf.add("2T " + tr + "Con - " + auxIndConN + " (NÃO POSSUI)");
								}
							}

						} else {
							if (cvm.containsKey(idX)) {
								value = (String) cvm.get(idX);

								System.out.println("A empresa " + value + ", com o id:" + idX
										+ ", não possui os dados do trimestre selecionado(In " + auxIndConL2 + " - 2T"
										+ tr + "), como deveria.");
								errorInf.add(value + " - 2T " + tr + " (NÃO POSSUI)");
							} else {
								System.out.println("Não foi encontrado o nome da empresa: " + idX
										+ ", e a mesma não possui os dados do trimestre selecionado(In " + auxIndConL2
										+ " - 2T" + tr + "), como deveria.");
								errorInf.add("2T " + tr + " (NÃO POSSUI) \n");
							}
						}
					}

				});
			});
		} else {
			System.out.println(
					"Não foi realizadas chamadas na Cp, pois a CVM não publicou dados p/o período selecionado (Ind "
							+ auxIndConL2 + " - 2T" + tr + ").");
		}

		System.out.println();
		System.out.println();

	}

	public void validateSecondQuarterCon(Map<Integer, String> cvm, Map<Integer, Map<Set<String>, Set<String>>> xml,
			String auxIndConN, String auxIndConL2, String tr) {
		System.out.println();
		if (!cvm.isEmpty()) {
			xml.forEach((idX, exerciseDate) -> {
				exerciseDate.forEach((indCon, quarter) -> {

					if (idX < 8000) {
						String value;

						if (quarter.contains("2T" + tr)) {

							if (indCon.contains(auxIndConN)) {

								if (cvm.containsKey(idX)) {
									value = (String) cvm.get(idX);

									System.out.println("A empresa " + value + ", com o id: " + idX + ", possui o Con "
											+ auxIndConL2 + " - 2T" + tr + ", no Broadcast.");
									errorInf.add(value + "salvando 2T - Con" + tr);
								} else {
									System.out.println("Não foi encontrado o nome da empresa: " + idX
											+ ", possui o Con " + auxIndConL2 + " - 2T" + tr + ", no Broadcast.");
									errorInf.add("salvando 2T - Con" + tr);
								}

							} else {
								if (cvm.containsKey(idX)) {
									value = (String) cvm.get(idX);

									System.out.println("A empresa " + value + ", com o id:" + idX + ", não possui Con "
											+ auxIndConL2 + " - 2T" + tr + ", no Broadcast, como deveria.");
									errorInf.add(value + " - 2T " + tr + "Con - " + auxIndConN + " (NÃO POSSUI)");
								} else {
									System.out.println("Não foi encontrado o nome da empresa: " + idX
											+ ", e a mesma não possui Con" + auxIndConL2 + " - 2T" + tr
											+ ", no Broadcast, como deveria.");
									errorInf.add("2T " + tr + "Con - " + auxIndConN + " (NÃO POSSUI)");
								}
							}

						} else {
							if (cvm.containsKey(idX)) {
								value = (String) cvm.get(idX);

								System.out.println("A empresa " + value + ", com o id:" + idX
										+ ", não possui os dados do trimestre selecionado(Con " + auxIndConL2 + " - 2T"
										+ tr + "), como deveria.");
								errorInf.add(value + " - 2T " + tr + " (NÃO POSSUI)");
							} else {
								System.out.println("Não foi encontrado o nome da empresa: " + idX
										+ ", e a mesma não possui os dados do trimestre selecionado(Con " + auxIndConL2
										+ " - 2T" + tr + "), como deveria.");
								errorInf.add("2T " + tr + " (NÃO POSSUI) \n");
							}
						}
					}

				});
			});
		} else {
			System.out.println(
					"Não foi realizadas chamadas na Cp, pois a CVM não publicou dados p/o período selecionado (Con "
							+ auxIndConL2 + " - 2T" + tr + ").");
		}

		System.out.println();
		System.out.println();

	}

	public void validateThirdQuarterInd(Map<Integer, String> cvm, Map<Integer, Map<Set<String>, Set<String>>> xml,
			String auxIndConN, String auxIndConL2, String tr) {

		System.out.println();
		if (!cvm.isEmpty()) {
			xml.forEach((idX, exerciseDate) -> {
				exerciseDate.forEach((indCon, quarter) -> {

					if (idX < 2000) {
						String value;

						if (quarter.contains("3T" + tr)) {

							if (indCon.contains(auxIndConN)) {

								if (cvm.containsKey(idX)) {
									value = (String) cvm.get(idX);

									System.out.println("A empresa " + value + ", com o id: " + idX + ", possui o In "
											+ auxIndConL2 + " - 3T" + tr + ", no Broadcast.");
									errorInf.add(value + "salvando 3T - In" + tr);
								} else {
									System.out.println("Não foi encontrado o nome da empresa: " + idX
											+ ", possui o In " + auxIndConL2 + " - 3T" + tr + ", no Broadcast.");
									errorInf.add("salvando 3T - In" + tr);
								}

							} else {
								if (cvm.containsKey(idX)) {
									value = (String) cvm.get(idX);

									System.out.println("A empresa " + value + ", com o id:" + idX + ", não possui In "
											+ auxIndConL2 + " - 3T" + tr + ", no Broadcast, como deveria.");
									errorInf.add(value + " - 3T " + tr + "In - " + auxIndConN + " (NÃO POSSUI)");
								} else {
									System.out.println("Não foi encontrado o nome da empresa: " + idX
											+ ", e a mesma não possui In" + auxIndConL2 + " - 3T" + tr
											+ ", no Broadcast, como deveria.");
									errorInf.add("3T " + tr + "In - " + auxIndConN + " (NÃO POSSUI)");
								}
							}

						} else {
							if (cvm.containsKey(idX)) {
								value = (String) cvm.get(idX);

								System.out.println("A empresa " + value + ", com o id:" + idX
										+ ", não possui os dados do trimestre selecionado(In " + auxIndConL2 + " - 3T"
										+ tr + "), como deveria.");
								errorInf.add(value + " - 3T " + tr + " (NÃO POSSUI)");
							} else {
								System.out.println("Não foi encontrado o nome da empresa: " + idX
										+ ", e a mesma não possui os dados do trimestre selecionado(In " + auxIndConL2
										+ " - 3T" + tr + "), como deveria.");
								errorInf.add("3T " + tr + " (NÃO POSSUI)");
							}
						}

						
					}

				});
			});
		} else {
			System.out.println(
					"Não foi realizadas chamadas na Cp, pois a CVM não publicou dados p/o período selecionado (Ind "
							+ auxIndConL2 + " - 3T" + tr + ").");
		}

		System.out.println();
		System.out.println();

	}

	public void validateThirdQuarterCon(Map<Integer, String> cvm, Map<Integer, Map<Set<String>, Set<String>>> xml,
			String auxIndConN, String auxIndConL2, String tr) {
		System.out.println();
		if (!cvm.isEmpty()) {
			xml.forEach((idX, exerciseDate) -> {
				exerciseDate.forEach((indCon, quarter) -> {

					if (idX < 8000) {

						String value;

						if (quarter.contains("3T" + tr)) {

							if (indCon.contains(auxIndConN)) {

								if (cvm.containsKey(idX)) {
									value = (String) cvm.get(idX);

									System.out.println("A empresa " + value + ", com o id: " + idX + ", possui o Con "
											+ auxIndConL2 + " - 3T" + tr + ", no Broadcast.");
									errorInf.add(value + "salvando 3T - Con " + tr);
								} else {
									System.out.println("Não foi encontrado o nome da empresa: " + idX
											+ ", possui o Con " + auxIndConL2 + " - 3T" + tr + ", no Broadcast.");
									errorInf.add("salvando 3T - Con" + tr);
								}

							} else {
								if (cvm.containsKey(idX)) {
									value = (String) cvm.get(idX);

									System.out.println("A empresa " + value + ", com o id:" + idX + ", não possui Con "
											+ auxIndConL2 + " - 3T" + tr + ", no Broadcast, como deveria.");
									errorInf.add(value + " - 3T " + tr + "Con - " + auxIndConN + " (NÃO POSSUI)");
								} else {
									System.out.println("Não foi encontrado o nome da empresa: " + idX
											+ ", e a mesma não possui Con" + auxIndConL2 + " - 3T" + tr
											+ ", no Broadcast, como deveria.");
									errorInf.add("3T " + tr + "Con - " + auxIndConN + " (NÃO POSSUI)");
								}
							}

						} else {
							if (cvm.containsKey(idX)) {
								value = (String) cvm.get(idX);

								System.out.println("A empresa " + value + ", com o id:" + idX
										+ ", não possui os dados do trimestre selecionado(Con " + auxIndConL2 + " - 3T"
										+ tr + "), como deveria.");
								errorInf.add(value + " - 3T " + tr + " (NÃO POSSUI)");
							} else {
								System.out.println("Não foi encontrado o nome da empresa: " + idX
										+ ", e a mesma não possui os dados do trimestre selecionado(Con " + auxIndConL2
										+ " - 3T" + tr + "), como deveria.");
								errorInf.add("3T " + tr + " (NÃO POSSUI)");
							}
						}

					}

				});
			});
		} else {
			System.out.println(
					"Não foi realizadas chamadas na Cp, pois a CVM não publicou dados p/o período selecionado (Con "
							+ auxIndConL2 + " - 3T" + tr + ").");
		}

		System.out.println();
		System.out.println();

	}

	public void validateFourthQuarterInd(Map<Integer, String> cvm, Map<Integer, Map<Set<String>, Set<String>>> xml,
			String auxIndConN, String auxIndConL2, String tr) {

		System.out.println();
		if (!cvm.isEmpty()) {
			xml.forEach((idX, exerciseDate) -> {
				exerciseDate.forEach((indCon, quarter) -> {

					if (idX < 8000) {

						if (quarter.contains("4T" + tr)) {
							if (indCon.contains(auxIndConN)) {
								System.out.println(
										"Conforme as chamadas realizadas na CP pelo o id da CVM, a empresa com o id: "
												+ idX + ", possui o Ind " + auxIndConL2 + " - 4T" + tr
												+ ", no Broadcast.");

							} else {
								System.out.println(
										"Conforme as chamadas realizadas na CP pelo o id da CVM, a empresa com o id:"
												+ idX + ", não possui Ind " + auxIndConL2 + " - 4T" + tr
												+ ", no Broadcast, como deveria.");
							}
						} else {
							System.out.println(
									"Conforme as chamadas realizadas na CP pelo o id da CVM, a empresa com o id:" + idX
											+ ", porém a mesma não possui os dados do trimestre selecionado(Ind "
											+ auxIndConL2 + " - 4T" + tr + "), como deveria.");
						}

					}

				});
			});
		} else {
			System.out.println(
					"Não foi realizadas chamadas na Cp, pois a CVM não publicou dados p/o período selecionado (Ind "
							+ auxIndConL2 + " - 4T" + tr + ").");
		}

		System.out.println();
		System.out.println();

	}

	public void validateFourthQuarterCon(Map<Integer, String> cvm, Map<Integer, Map<Set<String>, Set<String>>> xml,
			String auxIndConN, String auxIndConL2, String tr) {
		System.out.println();
		if (!cvm.isEmpty()) {
			xml.forEach((idX, exerciseDate) -> {
				exerciseDate.forEach((indCon, quarter) -> {

					if (idX < 1000) {

						if (quarter.contains("4T" + tr)) {
							if (indCon.contains(auxIndConN)) {
								System.out.println(
										"Conforme as chamadas realizadas na CP pelo o id da CVM, a empresa com o id: "
												+ idX + ", possui o Con " + auxIndConL2 + " - 4T" + tr
												+ ", no Broadcast.");

							} else {
								System.out.println(
										"Conforme as chamadas realizadas na CP pelo o id da CVM, a empresa com o id:"
												+ idX + ", não possui Con " + auxIndConL2 + " - 4T" + tr
												+ ", no Broadcast, como deveria.");
							}
						} else {
							System.out.println(
									"Conforme as chamadas realizadas na CP pelo o id da CVM, a empresa com o id:" + idX
											+ ", porém a mesma não possui os dados do trimestre selecionado(Con "
											+ auxIndConL2 + " - 4T" + tr + "), como deveria.");
						}
					}

				});
			});
		} else {
			System.out.println(
					"Não foi realizadas chamadas na Cp, pois a CVM não publicou dados p/o período selecionado (Con "
							+ auxIndConL2 + " - 4T" + tr + ").");
		}

		System.out.println();
		System.out.println();

	}

}
