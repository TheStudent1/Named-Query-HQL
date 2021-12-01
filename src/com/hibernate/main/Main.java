package com.hibernate.main;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.model.Product;
import com.hibernate.utils.HibernateUtils;

public class Main {

	public static void main(String[] args) {

		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();

		List<Product> products = new ArrayList<Product>();

		// Q1 : Afficher tous les produits qui se trouve dans la table PRODUIT.

		System.out.println("1- Afficher tous les produits qui se trouve dans la table Product");
		products = s.getNamedQuery("getAll").getResultList();

		for (Product p : products) {
			System.out.println(p.toString());
		}

		// Q2 :Afficher tous les produits qui ont un prix d’achat > 200 et qui ont un
		// nom qui commence par A.
		System.out.println(
				"2- Afficher tous les produits qui ont un prix d’achat > 12 et qui ont un nom qui commence par A");

		products = s.getNamedQuery("getSup12A").setParameter("name", "D%").setParameter("price", 12.00).getResultList();

		for (Product p : products) {
			System.out.println(p.toString());
		}

		// Q3 :Afficher tous les produit qui ont un prix de vente entre 100 et 1000.
		System.out.println("3- Afficher tous les produit qui ont un prix de vente entre 100 et 1000");

		products = s.getNamedQuery("getPriceBetween100&1000").setParameter("pr1", 100.0).setParameter("pr2", 1000.0)
				.getResultList();

		for (Product p : products) {
			System.out.println(p.toString());
		}

		// Q4 :Afficher la somme des prix de vente et prix d’achat par famille.
		System.out.println("4- Afficher la somme des prix de vente et prix d’achat par famille");

		List<Object[]> res = s.getNamedQuery("sumPricePerCat").getResultList();

		for (Object[] p : res) {
			System.out.println(p[0] + " --- " + p[1]);
		}

		// Q5 :Demander à l’utilisateur de saisir une famille, puis afficher tous les
		// produit de cette famille.
		System.out.println(
				"5- Demander à l’utilisateur de saisir une famille, puis afficher tous les produit de cette famille");

		Scanner sc = new Scanner(System.in);
		System.out.println("Tell me the Name of Category you want:");
		products = s.getNamedQuery("getbyCat").setParameter("category", sc.nextLine()).getResultList();

		for (Product p : products) {
			System.out.println(p.toString());
		}

		// Q6 :Demander à l’utilisateur de saisir les informations d’un produit, puis
		// ajouter ce produit dans la base de données.
		System.out.println(
				"5- Demander à l’utilisateur de saisir les informations d’un produit, puis ajouter ce produit dans la base de données");

		Product p1 = new Product();
		System.out.println("Enter the Information of the Product***");
		System.out.println("Name:");
		p1.setName(sc.nextLine());

		System.out.println("Category:");
		p1.setCategory(sc.nextLine());

		System.out.println("Price:");
		p1.setPrice(Double.valueOf(sc.nextLine()));

		s.save(p1);

		// Q7 :Demande à l’utilisateur d’augmenter le prix d’achat et le prix de vente
		// par 10% des produit qui appartiennent à une famille donnée par l’utilisateur.
		// Afficher le nombre de produit modifiés

		System.out.println("Enter the Category that you want to modify its price by 10%:");
		String cat = sc.nextLine();
		int nbrProductModified = s.getNamedQuery("modifyPriceByCat").setParameter("category", cat).executeUpdate();
		if (nbrProductModified >= 1) {
			System.out.println(nbrProductModified + " product modified!");
		} else
			System.out.println("We don't have this Categiry ...!");

		// Q8 :Supprimer tous les produits qui ont un nom qui commence par A. afficher
		// le nombre de produit supprimé.

		int nbrProductdeleted = s.getNamedQuery("deleteByFirstLetter").setParameter("name", "A%").executeUpdate();
		if (nbrProductdeleted >= 1) {
			System.out.println(nbrProductdeleted + " product modified!");
		} else
			System.out.println("No Product Start with A...");

	}

}
